package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.util.UtilClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class ProjectJDBCRepositoryImpl
    implements ProjectRepository {

    private final Connection connection;
    private final TaskRepository taskRepository;

    public ProjectJDBCRepositoryImpl(TaskRepository taskRepository) {
        connection = UtilClass.getConnection();
        this.taskRepository = taskRepository;
    }

    public ProjectJDBCRepositoryImpl() {
        this(null);
    }

    @Override
    public Project getElementById(String id) throws Exception {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `projects` WHERE `id` = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()){
            return null;
        }
        Project result = new Project(rs.getString("id"), rs.getString("name"),
                rs.getString("description"), rs.getString("userId"));
        ps.close();
        return result;
    }

    @Override
    public Project merge(Project element) throws Exception {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO `projects` (`id`, `name`, `description`, `userId`) " +
                "VALUES (?, ?, ?, ?)");
        ps.setString(1, element.getId());
        ps.setString(2, element.getName());
        ps.setString(3, element.getDescription());
        ps.setString(4, element.getUserId());
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
    public Collection<Project> getElements() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `projects`");
        Collection<Project> collection = new ArrayList<>();
        while (rs.next()){
            collection.add(new Project(rs.getString("id"), rs.getString("name"),
                    rs.getString("description"), rs.getString("userId")));
        }
        if (collection.isEmpty()){
            return null;
        }
        statement.close();
        return collection;
    }

    @Override
    public Project delete(String id) throws Exception {
        Project project = getElementById(id);
        PreparedStatement ps2 = connection.prepareStatement("DELETE FROM `tasks` WHERE `projectId` = ?");
        ps2.setString(1, id);
        ps2.executeUpdate();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM `projects` WHERE `id` = ?");
        ps.setString(1, id);
        if (ps.executeUpdate() == 0){
            return null;
        }
        ps2.close();
        ps.close();
        return project;
    }

    @Override
    public Collection<Project> getElementsByUserId(String id) throws Exception {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `projects` WHERE `userId` = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        Collection<Project> collection = new ArrayList<>();
        while (rs.next()){
            collection.add(new Project(rs.getString("id"), rs.getString("name"),
                    rs.getString("description"), rs.getString("userId")));
        }
        if (collection.isEmpty()){
            return null;
        }
        ps.close();
        return collection;
    }
}
