package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.util.UtilClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class TaskJDBCRepositoryImpl
    implements TaskRepository {

    private final Connection connection;

    public TaskJDBCRepositoryImpl() {
        connection = UtilClass.getConnection();
    }

    @Override
    public Task getElementById(String id) throws Exception {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `tasks` WHERE `id` = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()){
            return null;
        }
        Task result = new Task(rs.getString("id"), rs.getString("name"), rs.getString("description"),
                rs.getString("projectId"), rs.getString("userId"));
        ps.close();
        return result;
    }

    @Override
    public Task merge(Task element) throws Exception {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO `tasks` (`id`, `name`, `description`, `projectId`, `userId`) " +
                "VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, element.getId());
        ps.setString(2, element.getName());
        ps.setString(3, element.getDescription());
        ps.setString(4, element.getProjectId());
        ps.setString(5, element.getUserId());
        if (ps.executeUpdate() == 0){
            return null;
        }
        ps.close();
        return element;
    }

    @Override
    public void clearElements() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Task> getElements() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `tasks`");
        Collection<Task> collection = new ArrayList<>();
        while (rs.next()){
            collection.add(
                    new Task(rs.getString("id"), rs.getString("name"), rs.getString("description"),
                            rs.getString("projectId"), rs.getString("userId")));
        }
        if (collection.isEmpty()){
            return null;
        }
        statement.close();
        return collection;
    }

    @Override
    public Task delete(String id) throws Exception {
        Task task = getElementById(id);
        PreparedStatement ps = connection.prepareStatement("DELETE FROM `tasks` WHERE `id` = ?");
        ps.setString(1, id);
        if (ps.executeUpdate() == 0){
            return null;
        }
        ps.close();
        return task;
    }

    @Override
    public Collection<Task> getElementsByUserId(String id) throws Exception {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `tasks` WHERE `userId` = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Collection<Task> collection = new ArrayList<>();
        while (rs.next()){
            collection.add(new Task(rs.getString("id"), rs.getString("name"),
                    rs.getString("description"), rs.getString("projectId"),
                    rs.getString("userId")));
        }
        if (collection.isEmpty()){
            return null;
        }
        ps.close();
        return collection;
    }
}
