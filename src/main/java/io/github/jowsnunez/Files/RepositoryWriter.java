/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.jowsnunez.Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.regex.Pattern;
import org.openide.util.Exceptions;

/**
 *
 * @author el_fr
 */
public class RepositoryWriter extends AbstractWriter {

    public RepositoryWriter() {

    }

    public RepositoryWriter(String[] ctxPath) {
        super(ctxPath);

    }

   
    @Override
    public InputStream getTemplate() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream uri = classLoader.getResourceAsStream("IEntityRepository.java.template");
        return uri;
    }

    

}
