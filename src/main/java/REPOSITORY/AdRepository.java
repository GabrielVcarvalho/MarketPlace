package REPOSITORY;

import MODEL.AnuncioEntity;
import MODEL.UsuarioEntity;

import java.util.ArrayList;

public interface AdRepository {
    public void criarAnuncio(AnuncioEntity anuncio);

    public void removerAnuncio(AnuncioEntity anuncio);

    public AnuncioEntity lerAnuncioPeloId(int id);

    public AnuncioEntity lerAnuncioPeloNome(String nome);

    public ArrayList<UsuarioEntity> lerTodosOsAnuncios();

    public void adicionarLike(AnuncioEntity anuncio);

    public void adicionarDeslike(AnuncioEntity anuncio);
}