package org.dragard.projectmanager.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

public class AuthorizationServiceImpl
    implements AuthorizationService {

    private User activeUser;
    private final UserService userService;

    private final String TOKEN_DATA_TOKEN_EXPIRATION_DATE = "tokenExpirationDate";
    private final String TOKEN_DATA_TOKEN_CREATE_DATE = "tokenCreateDate";
    private final String TOKEN_DATA_USER_ID = "userId";
    private final String PROPERTIES_FILE_NAME = "config.properties";
    private final String PROPERTY_TOKEN_SECRET_KEY = "token.secretKey";
    private final String key;



    public AuthorizationServiceImpl(UserService userService) throws Exception {
        this.userService = userService;
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(Paths.get(this.getClass().getResource("/").toURI()).toFile(), PROPERTIES_FILE_NAME)));
        key = properties.getProperty(PROPERTY_TOKEN_SECRET_KEY);
    }

    @Override
    public String login(String login, String password) throws Exception {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()){
            throw new RuntimeException("Bad login or password");
        }
        final User user = userService.getElementByLogin(login);
        if (user == null || !user.getPassword().equals(password)){
            throw new RuntimeException("Bad login or password");
        }
        return createToken(user);
    }

    @Override
    public void logout() {
        activeUser = null;
    }

    @Override
    public User getActiveUser(String token) throws Exception {
        DefaultClaims claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        return userService.getElementById(claims.get(TOKEN_DATA_USER_ID, String.class));
    }

    @Override
    public boolean isLogged() {
        return activeUser != null;
    }

    private String createToken(User user) throws IOException {
        final Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(TOKEN_DATA_USER_ID, user.getId());
        Date date = new Date();
        tokenData.put(TOKEN_DATA_TOKEN_CREATE_DATE, date.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 30);
        tokenData.put(TOKEN_DATA_TOKEN_EXPIRATION_DATE, calendar.getTimeInMillis());
        System.out.println(calendar.getTimeInMillis());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
    }

    @Override
    public String checkToken(String token) throws IOException {
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new RuntimeException("Token corrupted");
        }
        Long expiredDate = claims.get(TOKEN_DATA_TOKEN_EXPIRATION_DATE, Long.class);
        if (expiredDate == null || new Date(expiredDate).after(new Date())){
            throw new RuntimeException("Invalid token");
        }
        return claims.get(TOKEN_DATA_USER_ID, String.class);
    }
}
