package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;
import org.dragard.projectmanager.util.UtilClass;

import java.util.Scanner;

public class AuthorizationChangePassword extends AbstractCommand{

    public AuthorizationChangePassword() {
        super("change_password", "Change password of current user", true);
    }

    @Override
    public void execute() {
        try {
            final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter old password:");
            final String oldPassword = scanner.nextLine();
            System.out.println("Enter new password:");
            final String password = UtilClass.getPassword(scanner.nextLine());
            Response response = getServiceLocator().getAuthorizationService().changePassword(oldPassword, password,
                    getServiceLocator().getAuthorizationService().getToken());
            if (response.getMessage() != null){
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
