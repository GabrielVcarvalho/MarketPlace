package service.anuncio.exceptions;

public class NullContentInComment extends RuntimeException {
    public NullContentInComment(String message) {
        super(message);
    }
}
