package SERVICE.Exceptions;

public class NameUserAlredyExists extends RuntimeException {
    public NameUserAlredyExists() {
        super("O nome de usuário informado já está em uso");
    }
}
