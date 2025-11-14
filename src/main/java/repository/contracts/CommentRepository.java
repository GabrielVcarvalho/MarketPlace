package repository.contracts;

import model.ComentarioEntity;

import java.util.ArrayList;

public interface CommentRepository {
    void criarComentario(ComentarioEntity comentarioEntity);

    void removerComentario(ComentarioEntity comentarioEntity);

    ArrayList<ComentarioEntity> lerComentariosDeUmAnuncio(int idAnuncio);
}
