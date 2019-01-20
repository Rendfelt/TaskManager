package org.dragard.projectmanager.client.util;

import org.dragard.projectmanager.client.endpoint.Response;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class UtilClass {

    private UtilClass() {
    }

    public static void checkResponse(Response response) throws Exception {
        if (response.getException() == null) {
            return;
        }
        Exception exception = (Exception) new ObjectInputStream(new ByteArrayInputStream(response.getException())).readObject();
        if (response.getException() != null) {
            throw exception;
        }
    }
}
