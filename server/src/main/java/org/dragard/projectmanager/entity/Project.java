package org.dragard.projectmanager.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
@NoArgsConstructor
public class Project extends AbstractJobEntity {

    public Project(String id, String name, String description, String userId) {
        super(id, name, description, userId);
    }

}
