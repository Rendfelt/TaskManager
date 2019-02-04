package org.dragard.projectmanager.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.Getter;
import lombok.Setter;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Getter
@Setter
@ApplicationScoped
public class AuthorizationServiceImpl
    implements AuthorizationService {

    @Inject
    private UserService userService;

    private final String TOKEN_DATA_TOKEN_EXPIRATION_DATE = "tokenExpirationDate";
    private final String TOKEN_DATA_TOKEN_CREATE_DATE = "tokenCreateDate";
    private final String TOKEN_DATA_USER_ID = "userId";
    private final String PROPERTIES_FILE_NAME = "config.properties";
    private final String PROPERTY_TOKEN_SECRET_KEY = "token.secretKey";
    private String key;

    public AuthorizationServiceImpl() throws IOException {
        Properties properties = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
        properties.load(is);
        key = properties.getProperty(PROPERTY_TOKEN_SECRET_KEY);
        is.close();
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
        // TODO: 04.02.2019 Realization
//        activeUser = null;
    }

    @Override
    public User getActiveUser(String token){
        DefaultClaims claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        return userService.getElementById(claims.get(TOKEN_DATA_USER_ID, String.class));
    }

    @Override
    public boolean isLogged() {
        return false;
    }

    public String refreshToken(String userId){
        return createToken(userService.getElementById(userId));
    }

    private String createToken(User user){
        final Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(TOKEN_DATA_USER_ID, user.getId());
        Date date = new Date();
        tokenData.put(TOKEN_DATA_TOKEN_CREATE_DATE, date.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        tokenData.put(TOKEN_DATA_TOKEN_EXPIRATION_DATE, calendar.getTimeInMillis());
        System.out.println(calendar.getTimeInMillis());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
    }

    /**
     * @return userId from token
     * */
    @Override
    public String checkToken(String token){
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new RuntimeException("Token corrupted");
        }
        Long expiredDate = claims.get(TOKEN_DATA_TOKEN_EXPIRATION_DATE, Long.class);
        if (expiredDate == null || new Date().after(new Date(expiredDate))){
            throw new RuntimeException("Invalid token");
        }
        return claims.get(TOKEN_DATA_USER_ID, String.class);
    }
}
