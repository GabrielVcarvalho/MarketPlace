package service.exceptions;

public class TriedConvertNullDTOForEntity extends RuntimeException {
    public TriedConvertNullDTOForEntity(String message) {
        super(message);
    }
}
