package SERVICE.Usuario.Exceptions;

public class InvalidUserToken extends RuntimeException {
    public InvalidUserToken() {
        super("O token informado não se refere a nenhum usuário");
    }
}
