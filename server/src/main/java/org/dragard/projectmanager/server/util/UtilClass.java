package org.dragard.projectmanager.server.util;

import org.dragard.projectmanager.server.entity.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class UtilClass {

    public static byte[] serializeExceptionToByteArray(Exception e){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(baos).writeObject(e);
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
        return baos.toByteArray();
    }

    public static String createToken(User user){
        return user.toString();
    }

}
