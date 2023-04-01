
package io.github.jowsnunez.Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;
import org.openide.util.Exceptions;

/**
 *
 * @author JowsNunez
 */
public abstract class AbstractWriter {

    private String content;
    private String[] strPath;
    private Path packagePath;
    private Path filePath;
    private FileManager fileManager;

    public AbstractWriter() {

    }

    public AbstractWriter(String[] strPath) {
        this.strPath = strPath;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void write() {
        String doc = this.getContent();
        doc = this.replace(Pattern.quote("${entityName}"), this.getFileManager().getClassName(), doc);
        doc = this.replace(Pattern.quote("${entityNameLower}"), this.getFileManager().getClassName().toLowerCase(), doc);
        doc = this.replace(Pattern.quote("${author}"), this.getFileManager().getAuthorName(), doc);
        doc = this.replace(Pattern.quote("${ID}"), this.getFileManager().getIdObjectType(), doc);
        doc = this.replace(Pattern.quote("${package}"), this.getFileManager().getPackageName(), doc);
     
        try {

            if (!Files.exists(this.getPackagePath())) {
                Files.createDirectory(this.getPackagePath());
            }
            Files.write(this.getFilePath(), doc.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);

        } catch (IOException ex) {

        }
    }

    public abstract InputStream getTemplate();

    public void makeTemplate() {
        InputStream inStream = this.getTemplate();

        String line;
        this.content = "";
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inStream));

            while ((line = bf.readLine()) != null) {
                content += line + "\n";
            }

        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);

        }

    }

    public String replace(String prefix, String value, String template) {
        template = template.replaceAll(prefix, value);
        return template;
    }

    public Path getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(Path packagePath) {
        this.packagePath = packagePath;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public String[] getStrPath() {
        return strPath;
    }

    public void setStrPath(String[] strPath) {
        this.strPath = strPath;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

}
