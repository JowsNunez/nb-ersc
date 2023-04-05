
package io.github.jowsnunez.files;


import java.io.InputStream;

/**
 *
 * @author JowsNunez
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
