
package io.github.jowsnunez.actions;

import org.openide.awt.StatusDisplayer;
import org.openide.modules.ModuleInstall;
import org.openide.util.RequestProcessor;
/**
 * 
 * @author JowsNunez
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {
         // Muestra un mensaje al usuario indicando que NetBeans se reiniciará después de la instalación del plugin
        StatusDisplayer.getDefault().setStatusText("El plugin se ha instalado correctamente. Se reiniciará NetBeans en unos segundos.");

        // Programa el reinicio de NetBeans después de que el usuario haya cerrado el mensaje de confirmación
        RequestProcessor.getDefault().post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000); // Espera 5 segundos antes de reiniciar NetBeans
                } catch (InterruptedException ex) {
                    // Manejo de excepción
                }
                    
            }
        });
    }

}
