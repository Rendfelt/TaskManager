package org.dragard.projectmanager.command;

import org.dragard.projectmanager.exception.TaskManagerException;
import org.dragard.projectmanager.service.DomainServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UserChangePasswordCommand extends AbstractCommand{

    public UserChangePasswordCommand() {
        super("change_password", "Change password", true);
    }

    @Override
    public void execute() throws NoSuchAlgorithmException, URISyntaxException, IOException {
        final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Enter password:");
        final byte[] password = MessageDigest.getInstance("MD5").digest(scanner.nextLine().getBytes(StandardCharsets.UTF_8));
        try {
            getServiceLocator().getUserService().changePassword(password, getServiceLocator().getAuthorizationService().getActiveUser());
        } catch (TaskManagerException abstractTaskManagerException) {
            abstractTaskManagerException.printStackTrace();
        }
        new DomainServiceImpl(getServiceLocator()).saveUserList();
    }
}
