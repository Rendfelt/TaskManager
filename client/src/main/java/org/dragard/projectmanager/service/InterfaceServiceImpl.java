package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.service.InterfaceService;

import javax.enterprise.context.ApplicationScoped;
import java.util.Scanner;

@ApplicationScoped
public class InterfaceServiceImpl
    implements InterfaceService {

    private Scanner scanner;

    public InterfaceServiceImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String getNewLine(){
        return scanner.nextLine();
    }
}
