package service.usuario.exceptions;

public class InvalidRole extends RuntimeException {
    public InvalidRole() {
        super("A role informada é inválida");
    }
}
