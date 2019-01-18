package org.dragard.projectmanager.entity;

import java.util.Arrays;
import java.util.Objects;

public class User extends AbstractEntity{

    private byte[] password;

    public User() {
    }

    public User(String id, String name, byte[] password) {
        super(id, name);
        this.password = password;
    }

    public byte[] getPassword() {
        return password;
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
                ", password='" + Arrays.toString(password) + '\'' +
                '}';
    }
}
