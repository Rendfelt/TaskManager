package org.dragard;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ProjectManager
{

    private final List<User> userList;
    private final GuiInterface guiInterface;
    private long maxID;

    public ProjectManager() {
        this.userList = new ArrayList<>();
        guiInterface = new ConsoleGui(this);

    }

    public static void main(String[] args )
    {
        ProjectManager projectManager = new ProjectManager();
        projectManager.guiInterface.showMenuLoggedOut();
    }

    public void addNewUser(final Pair<String, String> userData) {
        userList.add(new User(maxID++, userData.getKey(), userData.getValue()));
    }

}
