package org.dragard.projectmanager.server.util;

import org.dragard.projectmanager.server.entity.Response;
import org.dragard.projectmanager.server.entity.User;
import org.dragard.projectmanager.server.exception.TaskManagerException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static boolean checkToken(String token, Response response) throws TaskManagerException {
        if (token == null){
            throw new TaskManagerException("Invalid token, access denied");
        }
        return true;
    }

    public static byte[] getPassword(String s) throws NoSuchAlgorithmException {
        if (s == null || s.isEmpty()){
            return null;
        }
        return new String(MessageDigest.getInstance("MD5").digest(s.getBytes(StandardCharsets.UTF_8))).getBytes(StandardCharsets.UTF_8);

    }

}
