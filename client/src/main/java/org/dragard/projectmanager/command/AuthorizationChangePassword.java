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
            System.out.println("Enter old password:");
            final String oldPassword = getServiceLocator().getInterfaceService().getNewLine();
            System.out.println("Enter new password:");
            final String password = getServiceLocator().getInterfaceService().getNewLine();
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
