package service.Anuncio.Exceptions;

public class TitleOfAdAlreadyExists extends RuntimeException {
    public TitleOfAdAlreadyExists() {
        super("O nome de anúncio informado já está em uso");
    }
}
