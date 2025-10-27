package service.Anuncio.Exceptions;

public class AnuncioIdNotExists extends RuntimeException {
    public AnuncioIdNotExists(String message) {
        super(message);
    }
}
