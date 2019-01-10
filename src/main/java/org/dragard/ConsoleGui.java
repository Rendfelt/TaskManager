package org.dragard;

import java.util.Scanner;

public class ConsoleGui
    implements GuiInterface{

    private Scanner scanner;

    public ConsoleGui() {
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

        System.out.println(choice);
        if (choice == 1){
            try {
                loggingIn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            createNewUser();
        }

    }

    private void loggingIn(){
        scanner.nextLine();
        System.out.println("Enter login:");
        final String tempLogin = scanner.nextLine();
        System.out.println("Enter password:");
        final String tempPassword = scanner.nextLine();
        System.out.printf("%s - %s", tempLogin, tempPassword);
        showMenuLoggedIn();
    }

    private void createNewUser() {

    }

    @Override
    public void showMenuAdmin() {

    }
}
