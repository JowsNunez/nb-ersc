/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/contextAction.java to edit this template
 */
package io.github.jowsnunez.Actions;

import io.github.jowsnunez.Files.FileManager;
import io.github.jowsnunez.util.Constants;
import io.github.jowsnunez.util.Extractor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Build",
        id = "io.github.jowsnunez.Actions.CreateRepository"
)
@ActionRegistration(
        displayName = "#CTL_CreateRepository"
)
@ActionReference(path = "Loaders/text/x-java/Actions", position = 910)
@Messages("CTL_CreateRepository=CreateRepository")
public final class CreateRepository implements ActionListener {

    static final Logger LOGGER = Logger.getLogger("io.github.jowsnunez");
    private final DataObject context;
    private FileManager fileManager;

   

    public CreateRepository(DataObject context) {
        this.context = context;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        getContext(context);
        try {
            createRepository();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        LOGGER.log(Level.INFO, ev.toString());
        // TODO use context
    }

    private void createRepository() throws IOException {

        String doc = fileManager.doRepository();

        doc = fileManager.change(Pattern.quote("${entityName}"), fileManager.getClassName(), doc);
        doc = fileManager.change(Pattern.quote("${author}"), fileManager.getAuthorName(), doc);
        doc = fileManager.change(Pattern.quote("${ID}"), fileManager.getIdObjectType(), doc);
        doc = fileManager.change(Pattern.quote("${package}"), fileManager.getPackageName(), doc);

        String[] a = this.context.getPrimaryFile().getPath().split("/Entity/" + fileManager.getClassName() + ".java");

        LOGGER.log(Level.INFO, a[0]);

        Path directoryPath = Paths.get(a[0], "Repository");
        if (!Files.exists(directoryPath)) {
            Files.createDirectory(directoryPath);
        }

        Path filePath = Paths.get(a[0], "Repository/I" + fileManager.getClassName() + "Repository.java");

        try  {

            Files.write(filePath, doc.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);

        } catch (IOException ex) {
                LOGGER.log(Level.INFO, "Error al crear Repository");
        }

    }

    private void getContext(DataObject context) {

        String content;
       
        try (InputStream f = context.getPrimaryFile().getInputStream(); BufferedReader bf = new BufferedReader(new InputStreamReader(f, "UTF-8"));) {

            while ((content = bf.readLine()) != null) {
                if (!fileManager.isIsFoundId()) {
                    String[] idObject = fileManager.findIdAttrName(content);
                    if (idObject != null) {
                        fileManager.setIdObjectType(idObject[0]);
                        fileManager.setIdObjectName(idObject[1]);
                        fileManager.setIsFoundId(true);
                    }

                }
                if (!fileManager.isIsFoundClass()) {
                    String className = fileManager.findClassName(content);
                    if (className != null) {
                        fileManager.setClassName(className);
                        fileManager.setIsFoundClass(true);
                    }

                }
                if (!fileManager.isIsFoundAuthor()) {

                    String authorName = fileManager.findAuthorName(content);
                    if (authorName != null) {
                        fileManager.setAuthorName(authorName);
                        fileManager.setIsFoundAuthor(true);
                    }

                }
                if (!fileManager.isIsFoundPackage()) {

                    String[] packageName = fileManager.findPackageName(content);
                    if (packageName != null) {
                        fileManager.setPackageName(packageName[0]);
                        fileManager.setIsFoundPackage(true);
                    }

                }
                    
            }

            
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
