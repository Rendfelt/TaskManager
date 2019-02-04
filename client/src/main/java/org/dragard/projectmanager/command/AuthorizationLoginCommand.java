package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;
import org.dragard.projectmanager.util.UtilClass;

import java.util.Scanner;

public class AuthorizationLoginCommand extends AbstractCommand {

    public AuthorizationLoginCommand() {
        super("login", "Login", false);
    }

    @Override
    public void execute() {
        try {
            if (getServiceLocator().getAuthorizationService().isLogged()) {
                throw new Exception("Logout first");
            }
            System.out.println("Enter user login:");
            final String login = getServiceLocator().getInterfaceService().getNewLine();
            if (login.isEmpty()) {
                throw new Exception("Login is empty");
            }
            System.out.println("Enter password:");
            final String password = UtilClass.getPassword(getServiceLocator().getInterfaceService().getNewLine());
            Response response = getServiceLocator().getAuthorizationService().login(login, password);
            if (response.getMessage() != null){
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
