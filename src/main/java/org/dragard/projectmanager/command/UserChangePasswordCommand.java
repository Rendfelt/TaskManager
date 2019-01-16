package org.dragard.projectmanager.command;

import org.dragard.projectmanager.domain.DomainImpl;

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
    public void execute() throws URISyntaxException, IOException, NoSuchAlgorithmException {
        Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Enter password:");
        final byte[] password = MessageDigest.getInstance("MD5").digest(scanner.nextLine().getBytes(StandardCharsets.UTF_8));
        getServiceLocator().getUserService().changePassword(password, getServiceLocator().getAuthorizationService().getActiveUser());
        new DomainImpl(getServiceLocator()).saveUserList();
    }
}
