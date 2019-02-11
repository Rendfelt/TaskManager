package org.dragard.projectmanager.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import javafx.beans.NamedArg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

@Transactional
@Component
@NullAndEmptyChecker
@NoArgsConstructor
public class AuthorizationServiceImpl
    implements AuthorizationService {

    @Getter
    @Setter
    @Inject
    private UserService userService;

    private final String TOKEN_DATA_TOKEN_EXPIRATION_DATE = "tokenExpirationDate";
    private final String TOKEN_DATA_TOKEN_CREATE_DATE = "tokenCreateDate";
    private final String TOKEN_DATA_USER_ID = "userId";
    private final String PROPERTY_TOKEN_SECRET_KEY = "token.secretKey";
    private String key;

    @Inject
    @Setter
    @Qualifier("token")
    private Properties properties;

    @PostConstruct
    public void init(){
        key = properties.getProperty(PROPERTY_TOKEN_SECRET_KEY);
    }

    @Override
    public String login(
            @NamedArg(value = "login") @Nullable @NotEmpty String login,
            @NamedArg(value = "password") @Nullable @NotEmpty String password
    ) {
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
    public User getActiveUser(
            @NamedArg(value = "token") @Nullable @NotEmpty String token
    ){
        DefaultClaims claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        return userService.getElementById(claims.get(TOKEN_DATA_USER_ID, String.class));
    }

    @Override
    public boolean isLogged() {
        return false;
    }

    public String refreshToken(
            @NamedArg(value = "userId") @Nullable @NotEmpty String userId
    ){
        return createToken(userService.getElementById(userId));
    }

    private String createToken(
            @NamedArg(value = "user") @Nullable User user
    ){
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
    public String checkToken(
            @NamedArg(value = "token") @Nullable @NotEmpty String token
    ){
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
