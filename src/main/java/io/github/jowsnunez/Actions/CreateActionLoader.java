/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/contextAction.java to edit this template
 */
package io.github.jowsnunez.Actions;

import io.github.jowsnunez.Files.ControllerWriter;
import io.github.jowsnunez.Files.FileManager;
import io.github.jowsnunez.Files.RepositoryWriter;
import io.github.jowsnunez.Files.InterfaceServiceWriter;
import io.github.jowsnunez.Files.ServiceWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
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
public final class CreateActionLoader extends CreateAction {

    public CreateActionLoader(DataObject context) {
        super(context);
    }

}
