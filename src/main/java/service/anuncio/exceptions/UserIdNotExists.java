package service.anuncio.exceptions;

public class UserIdNotExists extends RuntimeException {
    public UserIdNotExists() {
        super("O id de vendedor informado não é válido");
    }
}