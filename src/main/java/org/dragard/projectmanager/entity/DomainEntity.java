package org.dragard.projectmanager.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class DomainEntity implements Serializable {

    private Collection<Project> projectList;
    private Collection<Task> taskList;

    public DomainEntity(Collection<Project> projectList, Collection<Task> taskList) {
        this.projectList = new ArrayList<>(projectList);
        this.taskList = new ArrayList<>(taskList);
    }

    public Collection<Project> getProjectList() {
        return projectList;
    }

    public Collection<Task> getTaskList() {
        return taskList;
    }
}
