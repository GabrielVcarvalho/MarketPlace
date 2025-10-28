package service.anuncio.exceptions;

public class TitleOfAdNotExits extends RuntimeException {
    public TitleOfAdNotExits(String message) {
        super(message);
    }
}
