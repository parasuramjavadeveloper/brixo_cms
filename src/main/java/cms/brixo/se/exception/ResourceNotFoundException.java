package cms.brixo.se.exception;

/**
 * To Handle HttpStatus 404 Exception.
 * @author Parasuram
 * @since 16-01-2021
 */
public class ResourceNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String exception) {
        super(exception);
    }
}
