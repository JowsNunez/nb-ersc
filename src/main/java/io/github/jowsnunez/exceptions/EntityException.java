
package io.github.jowsnunez.exceptions;

/**
 *
 * @author JowsNunez
 */
public class EntityException extends Exception {

    public EntityException(String message) {
        super(message);
    }

    public EntityException() {
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
