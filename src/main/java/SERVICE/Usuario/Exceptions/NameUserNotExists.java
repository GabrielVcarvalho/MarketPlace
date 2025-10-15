package SERVICE.Usuario.Exceptions;

public class NameUserNotExists extends RuntimeException {
    public NameUserNotExists() {
        super("O usuário informado não existe");
    }
}
