package org.dragard.projectmanager.client.command;

import org.dragard.projectmanager.client.endpoint.Response;
import org.dragard.projectmanager.client.exception.NoNameException;
import org.dragard.projectmanager.client.exception.TaskManagerException;
import org.dragard.projectmanager.client.util.UtilClass;

import java.util.Scanner;

public class AuthorizationLoginCommand extends AbstractCommand {

    public AuthorizationLoginCommand() {
        super("login", "Login", false);
    }

    @Override
    public void execute() {
        try {
            final Scanner scanner = getServiceLocator().getScanner();
            if (getServiceLocator().getAuthorizationService().isLogged()) {
                throw new TaskManagerException("Logout first");
            }
            System.out.println("Enter user login:");
            final String login = scanner.nextLine();
            if (login.isEmpty()) {
                throw new NoNameException();
            }
            System.out.println("Enter password:");
            final byte[] password = UtilClass.getPassword(scanner.nextLine());
            Response response = getServiceLocator().getAuthorizationService().login(login, password);
            if (response.getMessage() != null){
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
