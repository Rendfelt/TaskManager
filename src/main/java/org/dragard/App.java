package org.dragard;


/**
 *
 */
public class App
{
    public static void main( String[] args )
    {
        GuiInterface guiInstance = new ConsoleGui();
        guiInstance.showMenuLoggedOut();
    }
}
