package SERVICE.Exceptions;

public class WrongPassword extends RuntimeException {
    public WrongPassword() {
        super("A senha informada está incorreta");
    }
}
