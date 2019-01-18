package org.dragard.projectmanager.command;

import org.dragard.projectmanager.exception.AbstractTaskManagerExceptionImpl;
import org.dragard.projectmanager.service.DomainServiceImpl;
import org.dragard.projectmanager.exception.NoNameException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UserLoginCommand extends AbstractCommand {

    public UserLoginCommand() {
        super("login", "Login", false);
    }

    @Override
    public void execute() {
        try {
            final Scanner scanner = getServiceLocator().getScanner();
            if (getServiceLocator().getAuthorizationService().isLogged()) {
                throw new AbstractTaskManagerExceptionImpl("Logout first");
            }
            System.out.println("Enter user login:");
            final String login = scanner.nextLine();
            if (login.isEmpty()) {
                throw new NoNameException();
            }
            System.out.println("Enter password:");
            final byte[] password = MessageDigest.getInstance("MD5").digest(scanner.nextLine().getBytes(StandardCharsets.UTF_8));
            getServiceLocator().getAuthorizationService().login(login, password);
            new DomainServiceImpl(getServiceLocator()).loadSerialization();
        } catch (NoNameException | ClassNotFoundException | AbstractTaskManagerExceptionImpl | URISyntaxException | IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
