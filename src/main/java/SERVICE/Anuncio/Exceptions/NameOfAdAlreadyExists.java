package SERVICE.Anuncio.Exceptions;

public class NameOfAdAlreadyExists extends RuntimeException {
    public NameOfAdAlreadyExists() {
        super("O nome de anúncio informado já está em uso");
    }
}
