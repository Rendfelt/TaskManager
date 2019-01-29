package org.dragard.projectmanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Task extends AbstractJobEntity {

    @ManyToOne
    @JoinColumn(name="projectId", nullable=false, updatable=false)
    private Project project;

    public static Task newInstance(String name, String description, User user, Project project) {
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setName(name);
        task.setDescription(description);
        task.setUser(user);
        task.setProject(project);
        return task;
    }

}
