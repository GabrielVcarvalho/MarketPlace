package REPOSITORY;

import DAO.AnuncioDAO;
import MODEL.AnuncioEntity;
import MODEL.UsuarioEntity;

import java.util.ArrayList;

public class AnuncioRepository implements AdRepository {
    private AnuncioDAO anuncioDAO;

    public AnuncioRepository(AnuncioDAO anuncioDAO) {
        this.anuncioDAO = anuncioDAO;
    }

    @Override
    public void criarAnuncio(AnuncioEntity anuncio) {
        anuncioDAO.createAnuncio(anuncio.getIdVendedor(),
                anuncio.getTitulo(), anuncio.getDescricao());
    }

    @Override
    public void removerAnuncio(AnuncioEntity anuncio) {
        anuncioDAO.deleteAnuncio(anuncio.getTitulo());
    }

    @Override
    public AnuncioEntity lerAnuncioPeloId(int id) {
        return null;
    }

    @Override
    public AnuncioEntity lerAnuncioPeloNome(String nome) {
        return null;
    }

    @Override
    public ArrayList<UsuarioEntity> lerTodosOsAnuncios() {
        return null;
    }

    @Override
    public void adicionarLike(AnuncioEntity anuncio) {

    }

    @Override
    public void adicionarDeslike(AnuncioEntity anuncio) {

    }
}
