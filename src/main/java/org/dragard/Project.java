package org.dragard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project
        implements Serializable {

        private final List<Task> taskList;
        private final String name;

    public Project(final String name) {
        this.name = name;
        taskList = new ArrayList<>();
    }
}
