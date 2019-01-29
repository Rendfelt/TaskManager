/*
package org.dragard.projectmanager.service;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter;
import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.service.DomainService;
import org.dragard.projectmanager.entity.DomainEntity;
import org.dragard.projectmanager.entity.User;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Collection;

public class DomainServiceImpl
    implements DomainService {

    private final ServiceLocator serviceLocator;

    public DomainServiceImpl(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void saveUserList() throws URISyntaxException, IOException {
        DomainEntity domainEntity = null;
        try {
            domainEntity = new DomainEntity(serviceLocator.getProjectService().getElements(),
                    serviceLocator.getTaskService().getElements(), serviceLocator.getUserService().getElements());
        } catch (Exception e) {
            e.printStackTrace();
        }
        final File saveFile = getUserListSaveFile();
        saveFile.createNewFile();
        final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
        oos.writeObject(domainEntity.getUserList());
    }

    @Override
    public void loadUserList() throws Exception {
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

    private File getSaveJSONFile() throws URISyntaxException {
        final User user = serviceLocator.getAuthorizationService().getActiveUser();
        return new File(Paths.get(this.getClass().getResource("/").toURI()).toFile(), user.getName().toLowerCase() + "JSON.json");
    }

    private File getSaveXMLFile() throws URISyntaxException {
        final User user = serviceLocator.getAuthorizationService().getActiveUser();
        return new File(Paths.get(this.getClass().getResource("/").toURI()).toFile(), user.getName().toLowerCase() + "XML.xml");
    }

    private File getUserListSaveFile() throws URISyntaxException {
        return new File(Paths.get(this.getClass().getResource("/").toURI()).toFile(), ".userlist");
    }

    @Override
    public void loadSerialization() throws IOException, ClassNotFoundException, URISyntaxException {
        final File saveFile = getSaveFile();
        if (!saveFile.exists()){
            throw new IOException("No saved data");
        }
        final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
        DomainEntity domainEntity = (DomainEntity) ois.readObject();
        try {
            loadInMemory(domainEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadXML() throws URISyntaxException, IOException {
        loadMapper(new XmlMapper(), getSaveXMLFile());
    }

    @Override
    public void loadJSON() throws URISyntaxException, IOException {
        loadMapper(new ObjectMapper(), getSaveJSONFile());
    }

    private void loadMapper(ObjectMapper objectMapper, File saveFile) throws IOException {
        if (!saveFile.exists()){
            throw new IOException("No saved data");
        }
        DomainEntity domainEntity = objectMapper.readValue(saveFile, DomainEntity.class);
        try {
            loadInMemory(domainEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadInMemory(DomainEntity domainEntity) throws Exception {
        serviceLocator.getProjectService().clearElements();
        serviceLocator.getProjectService().persist(domainEntity.getProjectList());
        serviceLocator.getTaskService().clearElements();
        serviceLocator.getTaskService().persist(domainEntity.getTaskList());
        serviceLocator.getUserService().clearElements();
        serviceLocator.getUserService().persist(domainEntity.getUserList());
    }

    @Override
    public void saveSerialization() throws IOException, URISyntaxException {
        DomainEntity domainEntity = null;
        try {
            domainEntity = new DomainEntity(serviceLocator.getProjectService().getElements(),
                    serviceLocator.getTaskService().getElements(), serviceLocator.getUserService().getElements());
        } catch (Exception e) {
            e.printStackTrace();
        }
        final File saveFile = getSaveFile();
        saveFile.createNewFile();
        final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
        oos.writeObject(domainEntity);
    }

    @Override
    public void saveXML() throws URISyntaxException, IOException {
        saveMapper(new XmlMapper().writer(new DefaultXmlPrettyPrinter()), getSaveXMLFile());
    }

    @Override
    public void saveJSON() throws IOException, URISyntaxException {
        saveMapper(new ObjectMapper().writer(new DefaultPrettyPrinter()), getSaveJSONFile());
    }

    private void saveMapper(ObjectWriter objectWriter, File saveFile) throws IOException {
        DomainEntity domainEntity = null;
        try {
            domainEntity = new DomainEntity(serviceLocator.getProjectService().getElements(),
                    serviceLocator.getTaskService().getElements(), serviceLocator.getUserService().getElements());
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveFile.createNewFile();
        objectWriter.writeValue(new FileOutputStream(saveFile), domainEntity);
    }
}
*/
