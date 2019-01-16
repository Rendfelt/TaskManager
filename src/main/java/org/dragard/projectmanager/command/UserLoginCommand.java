package org.dragard.projectmanager.command;

import org.dragard.projectmanager.domain.DomainImpl;
import org.dragard.projectmanager.exception.NoNameException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UserLoginCommand extends AbstractCommand{

    public UserLoginCommand() {
        super("login", "Login", false);
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = getServiceLocator().getScanner();
            if (getServiceLocator().getAuthorizationService().isLogged()){
                throw new Exception("Logout first");
            }
            System.out.println("Enter user login:");
            final String login = scanner.nextLine();
            if (login.isEmpty()){
                throw new NoNameException();
            }
            System.out.println("Enter password:");
            final byte[] password = MessageDigest.getInstance("MD5").digest(scanner.nextLine().getBytes(StandardCharsets.UTF_8));
            getServiceLocator().getAuthorizationService().login(login, password);
            new DomainImpl(getServiceLocator()).load();
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
