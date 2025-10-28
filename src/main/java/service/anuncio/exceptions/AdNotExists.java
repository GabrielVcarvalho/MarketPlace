package service.anuncio.exceptions;

public class AdNotExists extends RuntimeException {
    public AdNotExists(String message) {
        super(message);
    }
}
