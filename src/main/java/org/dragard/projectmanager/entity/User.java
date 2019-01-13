package org.dragard.projectmanager.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User
        implements Serializable {

    private final long userId;
    private final String login;
    private final String password;
    private boolean isAdmin;
    private final List<org.dragard.projectmanager.entity.Project> projectList;

    public long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public List<org.dragard.projectmanager.entity.Project> getProjectList() {
        return projectList;
    }

    public User(long userId, String login, String password) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.isAdmin = false;
        projectList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", projectList=" + projectList +
                '}';
    }
}
