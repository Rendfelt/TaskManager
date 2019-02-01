package org.dragard;

import lombok.SneakyThrows;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class TestApp {

    @Test
    @SneakyThrows
    public void test(){
        byte[] digest = MessageDigest.getInstance("MD5").digest("root".getBytes(StandardCharsets.UTF_8));
        System.out.println(DatatypeConverter.printHexBinary(digest).toUpperCase());
    }
}
