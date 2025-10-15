package REPOSITORY;

public interface FeedBackRepository {
    public void adicionarLike(int idAnuncio, int idUsuario);

    public void removerLike(int idAnuncio, int idUsuario);

    public int contarLikes(int idAnuncio);

    public void adicinarDeslike(int idAnuncio, int idUsuario);

    public void removerDeslike(int idAnuncio, int idUsuario);

    public int contarDeslikes(int idAnuncio);
}