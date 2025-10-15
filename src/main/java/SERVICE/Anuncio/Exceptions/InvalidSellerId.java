package SERVICE.Anuncio.Exceptions;

public class InvalidSellerId extends RuntimeException {
    public InvalidSellerId() {
        super("O id de vendedor informado não é válido");
    }
}
