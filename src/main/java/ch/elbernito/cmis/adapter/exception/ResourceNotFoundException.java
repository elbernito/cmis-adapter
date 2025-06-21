package ch.elbernito.cmis.adapter.exception;

/**
 * Exception for resource-not-found errors.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
