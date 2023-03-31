/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.jowsnunez.Files;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

/**
 *
 * @author el_fr
 */
public class ServiceWriter extends AbstractWriter {

    public ServiceWriter(String[] strPath) {
        super(strPath);
    }

    @Override
    public InputStream getTemplate() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream uri = classLoader.getResourceAsStream("EntityService.java.template");
        return uri;
    }

}
