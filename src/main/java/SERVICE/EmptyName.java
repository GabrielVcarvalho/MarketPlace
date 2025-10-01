package SERVICE;

public class EmptyName extends RuntimeException {
    public EmptyName() {
        super("O nome de usuário está vazio");
    }
}