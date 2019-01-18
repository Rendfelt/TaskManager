package org.dragard.projectmanager.command;

import org.dragard.projectmanager.exception.TaskManagerException;
import org.dragard.projectmanager.service.DomainServiceImpl;
import org.dragard.projectmanager.exception.NoNameException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UserCreateCommand extends AbstractCommand{

    public UserCreateCommand() {
        super("create_user", "Create new user", false);
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter user login:");
            final String login = scanner.nextLine();
            if (login.isEmpty()){
                throw new NoNameException();
            }
            System.out.println("Enter password:");
            final byte[] password = MessageDigest.getInstance("MD5").digest(scanner.nextLine().getBytes(StandardCharsets.UTF_8));
            getServiceLocator().getUserService().create(login, password);
            new DomainServiceImpl(getServiceLocator()).saveUserList();
        } catch (NoNameException | TaskManagerException | URISyntaxException | IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
