package org.dragard.projectmanager.api;

import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;

import java.util.Map;
import java.util.Scanner;

public interface ServiceLocator {

    Map<String, Command> getCommandList();

    ProjectService getProjectService();

    TaskService getTaskService();

    Scanner getScanner();
}
