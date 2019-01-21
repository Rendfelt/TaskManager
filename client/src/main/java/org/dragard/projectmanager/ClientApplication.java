package org.dragard.projectmanager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class ClientApplication {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchAlgorithmException, IOException, URISyntaxException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

    }

}
