/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.jowsnunez.Files;

import java.io.InputStream;

/**
 *
 * @author el_fr
 */
public class ControllerWriter extends AbstractWriter {

    public ControllerWriter() {
        super();

    }

    public ControllerWriter(String[] strPath) {
        super(strPath);
    }

    @Override
    public InputStream getTemplate() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream uri = classLoader.getResourceAsStream("EntityRestController.java.template");
        return uri;
    }

}
