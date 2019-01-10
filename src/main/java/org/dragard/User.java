package org.dragard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User
        implements Serializable {

    private final long iserId;
    private final String login;
    private final String password;
    private final List<Task> taskList;
    private boolean isAdmin;

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
                "iserId=" + iserId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", taskList=" + taskList +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public User(long iserId, String login, String password, List<Task> taskList) {
        this.iserId = iserId;
        this.login = login;
        this.password = password;
        this.taskList = taskList;
        this.isAdmin = false;
    }

    public User(long userId, String login, String password) {
        this(userId, login, password, new ArrayList<Task>());
    }
}
