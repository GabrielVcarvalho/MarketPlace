package SERVICE.Anuncio.Exceptions;

public class AdNotExists extends RuntimeException {
    public AdNotExists(String message) {
        super(message);
    }
}
