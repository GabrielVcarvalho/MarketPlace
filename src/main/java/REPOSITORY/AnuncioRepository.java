package REPOSITORY;

import DAO.AnuncioDAO;
import MODEL.AnuncioEntity;

import java.util.ArrayList;

public class AnuncioRepository implements AdRepository {
    private final AnuncioDAO anuncioDAO;

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
        anuncioDAO.deleteAnuncio(anuncio.getIdVendedor(), anuncio.getTitulo());
    }

    @Override
    public AnuncioEntity lerAnuncioPeloId(int id) {
        return anuncioDAO.readAnuncioById(id);
    }

    @Override
    public AnuncioEntity lerAnuncioPeloNome(String nome) {
        return anuncioDAO.readAnuncioByName(nome);
    }

    @Override
    public ArrayList<AnuncioEntity> lerTodosOsAnuncios() {
        return anuncioDAO.readAllFromAnuncios();
    }
}
