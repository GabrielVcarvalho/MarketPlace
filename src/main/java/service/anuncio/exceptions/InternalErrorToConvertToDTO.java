package service.anuncio.exceptions;

public class InternalErrorToConvertToDTO extends RuntimeException {
    public InternalErrorToConvertToDTO(String message) {
        super(message);
    }
}
