package service.usuario.exceptions;

public class EmptyName extends RuntimeException {
    public EmptyName() {
        super("O nome de usuário está vazio");
    }
}