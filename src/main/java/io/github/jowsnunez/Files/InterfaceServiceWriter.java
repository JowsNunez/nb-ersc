
package io.github.jowsnunez.files;


import java.io.InputStream;

/**
 *
 * @author JowsNunez
 */
public class InterfaceServiceWriter extends AbstractWriter {

    public InterfaceServiceWriter(String[] strPath) {
        super(strPath);
    }

    @Override
    public InputStream getTemplate() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream uri = classLoader.getResourceAsStream("IEntityService.java.template");
        return uri;
    }

}
