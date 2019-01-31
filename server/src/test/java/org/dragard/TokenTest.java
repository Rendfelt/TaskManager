package org.dragard;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.repository.UserHibernateRepository;
import org.dragard.projectmanager.service.UserServiceImpl;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenTest {

    @Test
    public void test() throws Throwable {

        UserRepository ur = new UserHibernateRepository();
        UserService us = new UserServiceImpl(ur);

        User user = us.getElementByLogin("test");
        String token = getToken(user.getLogin(), user.getPassword(), us);
        System.out.println(token);

        getTokenInformation(token);


    }

    private void getTokenInformation(String token){

        String key = "secretKey";
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new RuntimeException("Token corrupted");
        }
        if (claims.get("token_expiration_date", Long.class) == null)
            throw new RuntimeException("Invalid token");
        Date expiredDate = new Date(claims.get("token_expiration_date", Long.class));
        if (expiredDate.after(new Date())){
            System.out.println(claims.get("clientType", String.class));
            System.out.println(claims.get("userId", String.class));
            System.out.println(claims.get("userName", String.class));
            System.out.println(claims.get("token_create_date", Long.class));
            System.out.println(new Date(claims.get("token_create_date", Long.class)));
            System.out.println(claims.get("token_expiration_date", Long.class));
            System.out.println(new Date(claims.get("token_expiration_date", Long.class)));
        }
        else
            throw new RuntimeException("Token expired date error");
    }


    public String getToken(String username, String password, UserService us) throws Exception {
        if (username == null || password == null)
            return null;
        User user = us.getElementByLogin(username);
        Map<String, Object> tokenData = new HashMap<>();
        if (password.equals(user.getPassword())) {
            tokenData.put("clientType", "user");
            tokenData.put("userId", user.getId());
            tokenData.put("userName", user.getLogin());
            Date date = new Date();
            tokenData.put("token_create_date", date.getTime());
            // TODO: 29.01.2019
            System.out.println(date.getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 30);
            tokenData.put("token_expiration_date", calendar.getTimeInMillis());
            // TODO: 29.01.2019
            System.out.println(calendar.getTimeInMillis());
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            String key = "secretKey";
            String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
            // TODO: 29.01.2019
            System.out.println(token);
            return token;
        } else {
            throw new Exception("Authentication error");
        }
    }
}
