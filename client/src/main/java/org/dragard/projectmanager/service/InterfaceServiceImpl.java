package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.service.InterfaceService;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
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
