package org.dragard.projectmanager.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class Application {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchAlgorithmException, IOException, URISyntaxException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

    }

}
