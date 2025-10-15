package SERVICE.Usuario.Exceptions;

public class NameUserAlreadyExists extends RuntimeException {
    public NameUserAlreadyExists() {
        super("O nome de usuário informado já está em uso");
    }
}
