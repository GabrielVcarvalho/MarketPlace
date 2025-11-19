package repository;

import dao.ComentarioDAO;
import model.ComentarioEntity;
import repository.contracts.CommentRepository;
import java.util.ArrayList;

public class ComentarioRepository implements CommentRepository {
    private final ComentarioDAO comentarioDAO;

    public ComentarioRepository(ComentarioDAO comentarioDAO) {
        this.comentarioDAO = comentarioDAO;
    }

    @Override
    public void criarComentario(ComentarioEntity comentarioEntity) {
        comentarioDAO.createComment(comentarioEntity);
    }

    @Override
    public void removerComentario(ComentarioEntity comentarioEntity) {

    }

    @Override
    public ArrayList<ComentarioEntity> lerComentariosDeUmAnuncio(int idAnuncio) {
        return comentarioDAO.readAllCommentsOfOneAd(idAnuncio);
    }
}
