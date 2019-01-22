package org.dragard.projectmanager.command;

import org.dragard.projectmanager.service.DomainServiceImpl;
import org.dragard.projectmanager.exception.NoNameException;
import org.dragard.projectmanager.util.UtilClass;

import java.util.Scanner;

public class UserCreateCommand extends AbstractCommand{

    public UserCreateCommand() {
        super("create_user", "Create new user", false);
    }

    @Override
    public void execute() throws Exception {
        try {
            Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter user login:");
            final String login = scanner.nextLine();
            if (login.isEmpty()){
                throw new NoNameException();
            }
            System.out.println("Enter password:");
            final String password = UtilClass.getPassword(scanner.nextLine());
            getServiceLocator().getUserService().create(login, password);
            new DomainServiceImpl(getServiceLocator()).saveUserList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
