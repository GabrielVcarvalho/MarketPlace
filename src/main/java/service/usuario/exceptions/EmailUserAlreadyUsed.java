package service.usuario.exceptions;

public class EmailUserAlreadyUsed extends RuntimeException {
    public EmailUserAlreadyUsed() {
        super("O email informado já está em uso");
    }
}
