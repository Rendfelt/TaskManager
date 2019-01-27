package org.dragard.projectmanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractJobEntity {

    @Column
    private String projectId;

    public Task(String id, String name, String description, String projectId, String userId) {
        super(id, name, description, userId);
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", projectId='" + projectId + '\'';
    }
}
