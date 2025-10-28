package service.anuncio.exceptions;

public class InvalidSellerId extends RuntimeException {
    public InvalidSellerId() {
        super("O id de vendedor informado não é válido");
    }
}