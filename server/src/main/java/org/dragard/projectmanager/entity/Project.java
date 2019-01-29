package org.dragard.projectmanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Project extends AbstractJobEntity {

    public static Project newInstance(String name, String description, User user) {
        Project project = new Project();
        project.setId(UUID.randomUUID().toString());
        project.setName(name);
        project.setDescription(description);
        project.setUser(user);
        return project;
    }

}
