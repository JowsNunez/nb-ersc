
package io.github.jowsnunez.actions;

import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
/**
 * 
 * @author JowsNunez
 */
    @ActionID(
            category = "Build",
            id = "io.github.jowsnunez.Actions.CreateRepository1"
    )
    @ActionRegistration(
            displayName = "#CTL_CreateRepository1"
    )

    @ActionReference(path = "Editors/text/x-java/Popup", position = 100)
    @Messages("CTL_CreateRepository1=CreateRepository")
    public final class CreateActionEditor extends CreateAction {

        public CreateActionEditor(DataObject context) {
            super(context);
        }

    }
