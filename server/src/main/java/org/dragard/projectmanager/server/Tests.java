package org.dragard.projectmanager.server;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.nio.file.Paths;

public class Tests {

    public static void main(String[] args) throws Exception {
        final Bootstrap bootstrap = new Bootstrap();
//        bootstrap.registry(clazzes);


/*        XmlMapper xmlMapper = new XmlMapper();
        File saveFile = new File(Paths.get(Tests.class.getResource("/").toURI()).toFile(),  "lala.xml");
        xmlMapper.writer().writeValue(new FileWriter(saveFile), bootstrap.getTaskService().getElements());*/

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new Exception("WTF"));

        System.out.println(new String(baos.toByteArray()));

        Exception ex = (Exception) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
        ex.printStackTrace();
        throw ex;

    }
}
