package io.github.jowsnunez.Files;

import io.github.jowsnunez.util.Extractor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.openide.util.Exceptions;

/**
 *
 * @author JowsNunez
 */
public class FileManager {

    private boolean isFoundId;
    private boolean isFoundAuthor;
    private boolean isFoundClass;
    private boolean isFoundPackage;
    private String className;
    private String authorName;
    private String idObjectType;
    private String idObjectName;
    private String packageName;

    public FileManager() {

        this.isFoundAuthor = false;
        this.isFoundClass = false;
        this.isFoundId = false;
        this.isFoundPackage = false;

    }

    public InputStream getRepositoryTemplate() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream uri = classLoader.getResourceAsStream("IRepository.java.template");
        return uri;

    }

    public String doRepository() {
        InputStream inStream = this.getRepositoryTemplate();

        String line;
        String content = "";
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inStream));

            while ((line = bf.readLine()) != null) {
                content += line + "\n";
            }
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
        return content;

    }

    public String change(String prefix, String value, String doc) {
        doc = doc.replaceAll(prefix, value);
        return doc;
    }

    public String[] findIdAttrName(String doc) {
        Extractor extractor = new Extractor();
        return extractor.extractObjAttName(doc);
    }
    public String findAuthorName(String doc) {
        Extractor extractor = new Extractor();
        return extractor.extractObjhAuthorName(doc);
    }
    public String[] findPackageName(String doc) {
        Extractor extractor = new Extractor();
        return extractor.extractObjPackageName(doc);
    }
     public String findClassName(String doc) {
        Extractor extractor = new Extractor();
        return extractor.extractObjClassName(doc);
    }

    public String getIdObjectName() {
        return idObjectName;
    }

    public void setIdObjectName(String idObjectName) {
        this.idObjectName = idObjectName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getIdObjectType() {
        return idObjectType;
    }

    public void setIdObjectType(String idObjectType) {
        this.idObjectType = idObjectType;
    }

    public boolean isIsFoundId() {
        return isFoundId;
    }

    public void setIsFoundId(boolean isFoundId) {
        this.isFoundId = isFoundId;
    }

    public boolean isIsFoundAuthor() {
        return isFoundAuthor;
    }

    public void setIsFoundAuthor(boolean isFoundAuthor) {
        this.isFoundAuthor = isFoundAuthor;
    }

    public boolean isIsFoundClass() {
        return isFoundClass;
    }

    public void setIsFoundClass(boolean isFoundClass) {
        this.isFoundClass = isFoundClass;
    }

    public boolean isIsFoundPackage() {
        return isFoundPackage;
    }

    public void setIsFoundPackage(boolean isFoundPackage) {
        this.isFoundPackage = isFoundPackage;
    }
    

}
