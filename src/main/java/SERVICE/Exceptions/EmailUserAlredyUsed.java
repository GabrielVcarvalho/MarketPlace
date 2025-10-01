package SERVICE.Exceptions;

public class EmailUserAlredyUsed extends RuntimeException {
    public EmailUserAlredyUsed() {
        super("O email informado já está em uso");
    }
}
