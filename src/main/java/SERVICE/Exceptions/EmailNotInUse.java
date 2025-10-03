package SERVICE.Exceptions;

public class EmailNotInUse extends RuntimeException {
    public EmailNotInUse() {
        super("O email informado não está cadastrado");
    }
}
