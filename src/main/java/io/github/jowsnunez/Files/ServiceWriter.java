
package io.github.jowsnunez.files;

import java.io.InputStream;

/**
 *
 * @author JowsNunez
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
