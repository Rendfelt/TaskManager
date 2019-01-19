package org.dragard.projectmanager.server.entity;

import java.io.Serializable;
import java.util.Collection;

public class DomainEntity implements Serializable {

    private Collection<Project> projectList;
    private Collection<Task> taskList;
    private Collection<User> userList;

    public DomainEntity() {
    }

    public DomainEntity(Collection<Project> projectList, Collection<Task> taskList, Collection<User> userList) {
        this.projectList = projectList;
        this.taskList = taskList;
        this.userList = userList;
    }

    public Collection<Project> getProjectList() {
        return projectList;
    }

    public Collection<Task> getTaskList() {
        return taskList;
    }

    public Collection<User> getUserList() {
        return userList;
    }

    public void setProjectList(Collection<Project> projectList) {
        this.projectList = projectList;
    }

    public void setTaskList(Collection<Task> taskList) {
        this.taskList = taskList;
    }

    public void setUserList(Collection<User> userList) {
        this.userList = userList;
    }
}
