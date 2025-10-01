package SERVICE;

public class EmptyPassword extends RuntimeException {
    public EmptyPassword() {
        super("A senha do usuário está vazia");
    }
}
