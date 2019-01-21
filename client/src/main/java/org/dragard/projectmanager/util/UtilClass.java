package org.dragard.projectmanager.util;

import org.dragard.projectmanager.endpoint.Response;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static String getPassword(String s) throws NoSuchAlgorithmException {
        if (s == null || s.isEmpty()){
            return null;
        }
        return new String(MessageDigest.getInstance("MD5").digest(s.getBytes(StandardCharsets.UTF_8)));
    }
}
