package org.dragard.projectmanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"password"})
@NoArgsConstructor
public class User extends AbstractEntity{

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    public static User newInstance(String login, String password){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setId(UUID.randomUUID().toString());
        return user;
    }


}
