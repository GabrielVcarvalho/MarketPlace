package service.Usuario.Exceptions;

public class InvalidRole extends RuntimeException {
    public InvalidRole() {
        super("A role informada é inválida");
    }
}
