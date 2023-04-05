
package io.github.jowsnunez.files;

import java.io.InputStream;

/**
 *
 * @author JowsNunez
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
