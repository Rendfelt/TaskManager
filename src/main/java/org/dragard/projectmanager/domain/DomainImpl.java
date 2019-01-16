package org.dragard.projectmanager.domain;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.domain.Domain;
import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.entity.User;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DomainImpl
    implements Domain {

    private final ServiceLocator serviceLocator;

    public DomainImpl(ServiceLocator serviceLocator) throws URISyntaxException {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void saveUserList() throws URISyntaxException, IOException {
        final File saveFile = getUserListSaveFile();
        saveFile.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
        oos.writeObject(serviceLocator.getUserService().getElements());
    }

    @Override
    public void loadUserList() throws URISyntaxException, IOException, ClassNotFoundException {
        final File saveFile = getUserListSaveFile();
        if (!saveFile.exists()){
            throw new IOException("No saved data");
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
        Map<String, User> users = serviceLocator.getUserService().getElements();
        users.clear();
        serviceLocator.getUserService().getElements().putAll((Map<String, User>) ois.readObject());
    }

    private File getSaveFile() throws URISyntaxException {
        User user = serviceLocator.getAuthorizationService().getActiveUser();
        URL resource = this.getClass().getResource("/");
        return new File(Paths.get(resource.toURI()).toFile(), user.getLogin().toLowerCase() + ".dat");
    }

    private File getUserListSaveFile() throws URISyntaxException {
        URL resource = this.getClass().getResource("/");
        return new File(Paths.get(resource.toURI()).toFile(), ".userlist");
    }

    @Override
    public void save() throws IOException, URISyntaxException {
        List<AbstractEntity> entityList = new ArrayList<>();
        final File saveFile = getSaveFile();
        entityList.addAll(serviceLocator.getProjectService().getElements().values());
        entityList.addAll(serviceLocator.getTaskService().getElements().values());
        saveFile.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
        oos.writeObject(entityList);
    }

    @Override
    public void load() throws IOException, ClassNotFoundException, URISyntaxException {
        List<AbstractEntity> entityList = new ArrayList<>();
        final File saveFile = getSaveFile();
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
