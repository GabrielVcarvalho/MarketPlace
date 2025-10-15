package SERVICE.Exceptions;

public class EmptyEmail extends RuntimeException {
    public EmptyEmail() {
        super("O email do usuário está vazio");
    }
}
