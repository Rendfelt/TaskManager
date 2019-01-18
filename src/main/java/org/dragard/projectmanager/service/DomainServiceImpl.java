package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.service.DomainService;
import org.dragard.projectmanager.entity.DomainEntity;
import org.dragard.projectmanager.entity.User;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class DomainServiceImpl
    implements DomainService {

    private final ServiceLocator serviceLocator;

    public DomainServiceImpl(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void saveUserList() throws URISyntaxException, IOException {
        final File saveFile = getUserListSaveFile();
        saveFile.createNewFile();
        final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
        oos.writeObject(new ArrayList<>(serviceLocator.getUserService().getElements()));
    }

    @Override
    public void loadUserList() throws URISyntaxException, IOException, ClassNotFoundException {
        final File saveFile = getUserListSaveFile();
        if (!saveFile.exists()){
            throw new IOException("No saved data");
        }
        final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
        serviceLocator.getUserService().clearElements();
        serviceLocator.getUserService().persist((Collection<User>) ois.readObject());
    }

    private File getSaveFile() throws URISyntaxException {
        final User user = serviceLocator.getAuthorizationService().getActiveUser();
        return new File(Paths.get(this.getClass().getResource("/").toURI()).toFile(), user.getName().toLowerCase() + ".dat");
    }

    private File getUserListSaveFile() throws URISyntaxException {
        return new File(Paths.get(this.getClass().getResource("/").toURI()).toFile(), ".userlist");
    }

    @Override
    public void save() throws IOException, URISyntaxException {
        final DomainEntity domainEntity = new DomainEntity(serviceLocator.getProjectService().getElements(), serviceLocator.getTaskService().getElements());
        final File saveFile = getSaveFile();
        saveFile.createNewFile();
        final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
        oos.writeObject(domainEntity);
    }

    @Override
    public void load() throws IOException, ClassNotFoundException, URISyntaxException {
        final File saveFile = getSaveFile();
        if (!saveFile.exists()){
            throw new IOException("No saved data");
        }
        final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
        final DomainEntity domain = (DomainEntity) ois.readObject();
        serviceLocator.getProjectService().clearElements();
        serviceLocator.getProjectService().persist(domain.getProjectList());
        serviceLocator.getTaskService().clearElements();
        serviceLocator.getTaskService().persist(domain.getTaskList());
    }

}
