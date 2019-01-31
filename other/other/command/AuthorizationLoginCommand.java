package org.dragard.projectmanager.command;

import org.dragard.projectmanager.exception.TaskManagerException;
import org.dragard.projectmanager.exception.NoNameException;
import org.dragard.projectmanager.util.UtilClass;

import java.util.Scanner;

public class AuthorizationLoginCommand extends AbstractCommand {

    public AuthorizationLoginCommand() {
        super("login", "Login", false);
    }

    @Override
    public void execute() throws Exception {
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
            final String password = UtilClass.getPassword(scanner.nextLine());
            getServiceLocator().getAuthorizationService().login(login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
