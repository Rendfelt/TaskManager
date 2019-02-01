package org.dragard;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TestApp {

    @Test
    @SneakyThrows
    public void test(){
/*
        File file = File.createTempFile("pref", "suf");
        System.out.println(file);
        InputStream is = getClass().getResourceAsStream("/test_dump_project_manager.sql");
        System.out.println(is);
        Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        while ((s = br.readLine()) !=null){
            System.out.println(s);
        }
*/
    }
}
