package service.exceptions;

public class NullDTO extends RuntimeException {
    public NullDTO(String message) {
        super(message);
    }
}
