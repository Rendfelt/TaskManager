package org.dragard.projectmanager.domain;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.domain.Domain;
import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainImpl
    implements Domain {

    private final ServiceLocator serviceLocator;
    private final List<AbstractEntity> entityList;
    private final File saveFile;

    public DomainImpl(ServiceLocator serviceLocator) throws URISyntaxException {
        this.serviceLocator = serviceLocator;
        entityList = new ArrayList<>();
        URL resource = this.getClass().getResource("/");
        saveFile = new File(Paths.get(resource.toURI()).toFile(), "data.dat");
    }

    @Override
    public void save() throws IOException {
        entityList.addAll(serviceLocator.getProjectService().getElements().values());
        entityList.addAll(serviceLocator.getTaskService().getElements().values());
        saveFile.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
        oos.writeObject(entityList);
    }

    @Override
    public void load() throws IOException, ClassNotFoundException {
        if (!saveFile.exists()){
            throw new IOException("No saved data");
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
        entityList.addAll((List<AbstractEntity>) ois.readObject());
        Map<String, Task> tasks = serviceLocator.getTaskService().getElements();
        Map<String, Project> projects = serviceLocator.getProjectService().getElements();
        tasks.clear();
        projects.clear();
        for (AbstractEntity entity :
                entityList) {
            if (entity instanceof Task){
                tasks.put(entity.getId(), (Task) entity);
            } else if (entity instanceof Project){
                projects.put(entity.getId(), (Project) entity);
            }
        }
    }
}
