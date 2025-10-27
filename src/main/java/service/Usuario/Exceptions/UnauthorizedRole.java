package service.Usuario.Exceptions;

public class UnauthorizedRole extends RuntimeException {
    public UnauthorizedRole(String message) {
        super(message);
    }
}
