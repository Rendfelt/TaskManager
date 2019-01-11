package org.dragard;

import javafx.util.Pair;
import org.dragard.Exceptions.NotUniqueUserException;

import java.util.Scanner;

public class ConsoleGui
    implements GuiInterface{

    private final Scanner scanner;
    private final ProjectManager projectManager;

    public ConsoleGui(final ProjectManager projectManager) {
        this.projectManager = projectManager;
        scanner = new Scanner(System.in);
    }

    @Override
    public void showMenuLoggedIn() {
        System.out.println("\n\nYou have logged in successfully. Congratulations");
    }

    @Override
    public void showMenuLoggedOut() {
        System.out.println("\nWelcome to Task Manager, your little helper and the best friend. " +
                "You can: \n1. Log in \nor \n2. Create new user \nand THEN log in =) Choose wisely" +
                "\n(enter number 1-2)");
        int choice;

        while (true){
            while (!scanner.hasNextInt()){
                System.out.println("It's not a number, try again, please");
                scanner.nextLine();
            }
            choice = scanner.nextInt();
            if (!((choice < 1) || (choice > 2))){
                break;
            }
            System.out.println("enter number 1-2");
        }

        if (choice == 1){
            loggingIn();
        } else {
            createNewUser();
        }

    }

    private void loggingIn(){

        final Pair<String, String> userData = readUserData();
        System.out.printf("%s - %s", userData.getKey(), userData.getValue());
        showMenuLoggedIn();

    }

    private Pair<String, String> readUserData(){
        scanner.nextLine();
        System.out.println("Enter login:");
        final String tempLogin = scanner.nextLine();
        System.out.println("Enter password:");
        final String tempPassword = scanner.nextLine();
        return new Pair<>(tempLogin, tempPassword);
    }

    private void createNewUser() {
        final Pair<String, String> userData = readUserData();
        try {
            projectManager.addNewUser(userData);
        } catch (NotUniqueUserException e) {
            System.out.println("Not unique login. Please try again");
        }
        showMenuLoggedOut();
    }

    @Override
    public void showMenuAdmin() {

    }
}
