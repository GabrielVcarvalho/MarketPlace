package service.usuario.exceptions;

public class UnauthorizedRole extends RuntimeException {
    public UnauthorizedRole(String message) {
        super(message);
    }
}
