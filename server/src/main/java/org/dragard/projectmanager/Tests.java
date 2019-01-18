package org.dragard.projectmanager;


import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

public class Tests {

    public static void main(String[] args) throws Exception {
        final Bootstrap bootstrap = new Bootstrap();
//        bootstrap.registry(clazzes);


        XmlMapper xmlMapper = new XmlMapper();
        File saveFile = new File(Paths.get(Tests.class.getResource("/").toURI()).toFile(),  "lala.xml");
        xmlMapper.writer().writeValue(new FileWriter(saveFile), bootstrap.getTaskService().getElements());

    }
}