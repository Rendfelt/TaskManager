package org.dragard.projectmanager.entity;

import java.util.Objects;

public class User extends AbstractEntity{

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public User() {
    }

    public User(String id, String name, String password) {
        super(id, name);
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", password='" + password + '\'';
    }
}
