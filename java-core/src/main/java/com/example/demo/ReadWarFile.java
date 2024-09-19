package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ReadWarFile {
    public void readWarFile() throws URISyntaxException, UnsupportedEncodingException {
        		Consumer<String> consumer=(str)->System.out.println("Hello World " + str);
		consumer.accept("new");
        String tempStr ="L:/project/demo/target/demo-0.0.1-SNAPSHOT.war";
        URI url= new URI(tempStr);
        File temp= new File(url);
        if (temp.isDirectory()){
            System.out.println("vao day ne "+ Arrays.toString(temp.list()));
        } else{
            System.out.println("khong mo file duo");
        }
        List<String> filenames = new ArrayList<>();
        try (JarFile jar = new JarFile(URLDecoder.decode(tempStr, StandardCharsets.UTF_8.name()))) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory()){
                    System.out.println(entry.getName());

                }
//                if (name.startsWith(dirname) && !dirname.equals(name)) {
//                    URL resource = Thread.currentThread().getContextClassLoader().getResource(name);
//                    filenames.add(resource.toString());
//                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
