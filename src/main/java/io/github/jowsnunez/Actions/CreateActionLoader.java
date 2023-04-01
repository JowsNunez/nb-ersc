
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
