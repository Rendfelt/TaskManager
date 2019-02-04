package org.dragard.projectmanager.event;

import org.dragard.projectmanager.api.event.ConsoleReadEvent;

public class ConsoleReadEventImpl
    implements ConsoleReadEvent {

    @Override
    public void fire(ConsoleReadEvent event) {
        System.out.println("fire " + event.getClass().getSimpleName());
    }
}
