package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;
import org.dragard.projectmanager.util.UtilClass;

import java.util.Scanner;

public class AuthorizationRegister extends AbstractCommand{

    public AuthorizationRegister() {
        super("register", "Create new user", false);
    }

    @Override
    public void execute() {
        try {
            final Scanner scanner = getServiceLocator().getScanner();
            if (getServiceLocator().getAuthorizationService().isLogged()) {
                throw new Exception("Logout first");
            }
            System.out.println("Enter user login:");
            final String login = scanner.nextLine();
            System.out.println("Enter password:");
            final String password = UtilClass.getPassword(scanner.nextLine());
            Response response = getServiceLocator().getAuthorizationService().register(login, password);
            if (response.getMessage() != null){
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
