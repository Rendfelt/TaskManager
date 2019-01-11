package org.dragard;


import javafx.util.Pair;
import org.dragard.Exceptions.NotUniqueUserException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ProjectManager
{

    private List<User> userList;
    private final GuiInterface guiInterface;
    private long maxID;

    public ProjectManager() {
        maxID = 0;
        this.userList = new ArrayList<>();
        guiInterface = new ConsoleGui(this);
        load();
        if ((userList != null) && (!userList.isEmpty())){
            for (User user :
                    userList) {
                maxID = user.getUserId() > maxID ? user.getUserId() : maxID;
            }
        }
    }

    public static void main(String[] args )
    {
        ProjectManager projectManager = new ProjectManager();
        projectManager.guiInterface.showMenuLoggedOut();
    }

    public void addNewUser(final Pair<String, String> userData) throws NotUniqueUserException {
        User user = new User(++maxID, userData.getKey(), userData.getValue());
        for (User u :
                userList) {
            if (user.equals(u)){
                throw new NotUniqueUserException();
            }
        }
        userList.add(user);
        save();
    }

    private File getSaveFile() throws URISyntaxException, FileNotFoundException {
        URL resource;
        File file;
        try {
            resource = ProjectManager.class.getResource("/data.dat");
            if (resource == null){
                throw new FileNotFoundException();
            }
            file = Paths.get(resource.toURI()).toFile();
            System.out.println(file.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw e;
        }
        return file;
    }

    private void save(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getSaveFile()));
            out.writeObject(userList);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println("\n\n\n!!!!!!!!!!!!!!!!!!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("\n\n\n??????????????");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(getSaveFile()));
            userList = (List<User>) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("\n\n\n==================");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println("\n\n\n!!!!!!!!!!!!!!!!!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("\n\n\n??????????????");
        }
    }

}
