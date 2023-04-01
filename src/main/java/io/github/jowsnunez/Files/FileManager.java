package io.github.jowsnunez.Files;

import io.github.jowsnunez.util.Extractor;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    private List<AbstractWriter> writers;
    private String className;
    private String authorName;
    private String idObjectType;
    private String idObjectName;
    private String packageName;
    private Extractor extractor;

    public FileManager() {
        this.writers = new ArrayList<>();
        this.isFoundAuthor = false;
        this.isFoundClass = false;
        this.isFoundId = false;
        this.isFoundPackage = false;
        this.extractor = new Extractor();

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

        return extractor.extractObjAttName(doc);
    }

    public String findAuthorName(String doc) {

        return extractor.extractObjhAuthorName(doc);
    }

    public String[] findPackageName(String doc) {

        return extractor.extractObjPackageName(doc);
    }

    public String findClassName(String doc) {

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

    public List<AbstractWriter> getWriters() {
        return writers;
    }

    public void setWriters(List<AbstractWriter> writers) {
        this.writers = writers;
    }

    public void writeAll() {
        for (AbstractWriter writer : writers) {
            if (writer != null) {
                writer.makeTemplate();
                writer.write();
            }

        }
    }

    public void addWriters(AbstractWriter abstractWriter) {
        abstractWriter.setFileManager(this);
        this.makePaths(abstractWriter);
        this.writers.add(abstractWriter);
    }

    private void makePaths(AbstractWriter abstractWriter) {
        String strFilePath;
        String strPackagePath;

        if (abstractWriter == null) {
            return;
        }

        if (abstractWriter instanceof RepositoryWriter) {
            strFilePath = "repository/I" + this.getClassName() + "Repository.java";
            strPackagePath = "repository";
        } else if (abstractWriter instanceof InterfaceServiceWriter) {
            strFilePath = "service/I" + this.getClassName() + "Service.java";
            strPackagePath = "service";
        } else if (abstractWriter instanceof ControllerWriter) {
            strFilePath = "controller/" + this.getClassName() + "RestController.java";
            strPackagePath = "controller";
        } else if (abstractWriter instanceof ServiceWriter) {
            strFilePath = "service/" + this.getClassName() + "Service.java";
            strPackagePath = "service";
        } else {
            return;
        }

        if (strPackagePath.isBlank() || strFilePath.isBlank()) {
            return;
        }

        abstractWriter.setFilePath(Paths.get(abstractWriter.getStrPath()[0], strFilePath));
        abstractWriter.setPackagePath(Paths.get(abstractWriter.getStrPath()[0], strPackagePath));

    }

}
