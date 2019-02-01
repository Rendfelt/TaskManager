package org.dragard.projectmanager.util;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilClass {

    private UtilClass() {
    }

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

    public static String getPassword(String s) throws NoSuchAlgorithmException {
        if (s == null || s.isEmpty()){
            return null;
        }
        byte[] digest = MessageDigest.getInstance("MD5").digest(s.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
