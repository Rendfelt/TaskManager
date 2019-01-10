package org.dragard;

import java.io.Serializable;
import java.util.Objects;

public class Task
        implements Serializable {

    private final long id;
    private final String description;
    private final String shortName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(description, task.description) &&
                Objects.equals(shortName, task.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, shortName);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getShortName() {
        return shortName;
    }

    public Task(long id, String description, String shortName) {
        this.id = id;
        this.description = description;
        this.shortName = shortName;
    }
}
