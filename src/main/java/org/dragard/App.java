package org.dragard;


/**
 * why?
 */
public class App
{
    public static void main( String[] args )
    {
        GuiInterface guiInstance = new ConsoleGui();
        guiInstance.showMenuLoggedOut();
    }
}
