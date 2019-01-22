package org.dragard.projectmanager.command;

import org.dragard.projectmanager.exception.TaskManagerException;
import org.dragard.projectmanager.service.DomainServiceImpl;
import org.dragard.projectmanager.util.UtilClass;

import java.util.Scanner;

public class UserChangePasswordCommand extends AbstractCommand{

    public UserChangePasswordCommand() {
        super("change_password", "Change password", true);
    }

    @Override
    public void execute() throws Exception {
        final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Enter password:");
        final String password = UtilClass.getPassword(scanner.nextLine());
        try {
            getServiceLocator().getUserService().changePassword(password, getServiceLocator().getAuthorizationService().getActiveUser());
        } catch (TaskManagerException abstractTaskManagerException) {
            abstractTaskManagerException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new DomainServiceImpl(getServiceLocator()).saveUserList();
    }
}
