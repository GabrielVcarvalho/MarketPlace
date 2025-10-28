package service.anuncio.exceptions;

public class AnuncioIdNotExists extends RuntimeException {
    public AnuncioIdNotExists(String message) {
        super(message);
    }
}
