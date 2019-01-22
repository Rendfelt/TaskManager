package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.util.UtilClass;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class UserJDBCRepositoryImpl
    implements UserRepository {

    private final Connection connection;

    public UserJDBCRepositoryImpl() {
        connection = UtilClass.getConnection();
    }

    @Override
    public User getElementByLogin(String login) throws Exception {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `users` WHERE `name` = ?");
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()){
            return null;
        }
        User result = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        rs.close();
        return result;
    }

    @Override
    public Collection<User> getElements() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `users`");
        Collection<User> collection = new ArrayList<>();
        while (rs.next()){
            collection.add(
                    new User(rs.getString("id"), rs.getString("name"), rs.getString("password")));
        }
        if (collection.isEmpty()){
            return null;
        }
        statement.close();
        return collection;
    }

    @Override
    public User getElementById(String id) throws Exception {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `users` WHERE `id` = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()){
            return null;
        }
        User result = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        ps.close();
        return result;
    }

    @Override
    public void clearElements() {
        throw new UnsupportedOperationException();
    }

    @Override
    public User delete(String id) throws Exception {
        User user = getElementById(id);
        PreparedStatement ps = connection.prepareStatement("DELETE FROM `users` WHERE id = ?");
        ps.setString(1, id);
        if (ps.executeUpdate() == 0){
            return null;
        }
        ps.close();
        return user;
    }

    @Override
    public User merge(User element) throws Exception {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO `users` (`id`, `name`, `password`) " +
                                                                "VALUES (?, ?, ?)");
        ps.setString(1, element.getId());
        ps.setString(2, element.getName());
        ps.setString(3, element.getPassword());
        if (ps.executeUpdate() == 0){
            return null;
        }
        ps.close();
        return element;
    }

    @Override
    public Collection<User> getElementsByUserId(String id) throws Exception {
        throw new OperationNotSupportedException();
    }
}
