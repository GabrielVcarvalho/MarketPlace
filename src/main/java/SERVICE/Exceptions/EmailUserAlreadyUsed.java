package SERVICE.Exceptions;

public class EmailUserAlreadyUsed extends RuntimeException {
    public EmailUserAlreadyUsed() {
        super("O email informado já está em uso");
    }
}
