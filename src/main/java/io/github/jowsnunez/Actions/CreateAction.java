/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.jowsnunez.Actions;

import io.github.jowsnunez.Files.ControllerWriter;
import io.github.jowsnunez.Files.FileManager;
import io.github.jowsnunez.Files.InterfaceServiceWriter;
import io.github.jowsnunez.Files.RepositoryWriter;
import io.github.jowsnunez.Files.ServiceWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;

/**
 *
 * @author el_fr
 */
public class CreateAction implements ActionListener {

    private final DataObject context;
    private String ctxPath[];
    private FileManager fileManager;

    public CreateAction(DataObject context) {
        this.context = context;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        getContext(context);
        this.ctxPath = this.context.getPrimaryFile().getPath().split("/(Entity|entity)/" + fileManager.getClassName() + ".java");
        this.fileManager.addWriters(new RepositoryWriter(ctxPath));
        this.fileManager.addWriters(new InterfaceServiceWriter(ctxPath));
        this.fileManager.addWriters(new ServiceWriter(ctxPath));
        this.fileManager.addWriters(new ControllerWriter(ctxPath));
        this.fileManager.writeAll();

        // TODO use context
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