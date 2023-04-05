
package io.github.jowsnunez.actions;

import io.github.jowsnunez.files.ControllerWriter;
import io.github.jowsnunez.files.FileManager;
import io.github.jowsnunez.files.InterfaceServiceWriter;
import io.github.jowsnunez.files.RepositoryWriter;
import io.github.jowsnunez.files.ServiceWriter;
import io.github.jowsnunez.exceptions.EntityException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;

/**
 *
 * @author JowsNunez
 */
public class CreateAction implements ActionListener {
    
    private static final Logger LOGGER = Logger.getLogger("io.github.jowsnunez.CreateAction");
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
        verifyEntity();
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
    
    private void verifyEntity() {
        try {
            if (ctxPath[0].endsWith("Entity")) {
                throw new EntityException("You must define package with lower case \"entity\"", new Throwable("ss"));
            }
        } catch (EntityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }
    
}
