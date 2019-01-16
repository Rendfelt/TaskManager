package org.dragard.projectmanager.command;

import org.dragard.projectmanager.domain.DomainImpl;
import org.dragard.projectmanager.exception.NoNameException;

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
            new DomainImpl(getServiceLocator()).saveUserList();
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
