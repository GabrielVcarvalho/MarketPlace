package service.usuario.exceptions;

public class EmptyEmail extends RuntimeException {
    public EmptyEmail() {
        super("O email do usuário está vazio");
    }
}
