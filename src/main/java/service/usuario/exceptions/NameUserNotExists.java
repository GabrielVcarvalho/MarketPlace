package service.usuario.exceptions;

public class NameUserNotExists extends RuntimeException {
    public NameUserNotExists() {
        super("O usuário informado não existe");
    }
}
