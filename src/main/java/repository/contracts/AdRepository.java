package repository.contracts;

import model.AnuncioEntity;

import java.util.ArrayList;

public interface AdRepository {
    public void criarAnuncio(AnuncioEntity anuncio);

    public void removerAnuncio(AnuncioEntity anuncio);

    public AnuncioEntity lerAnuncioPeloId(int id);

    public AnuncioEntity lerAnuncioPeloNome(String nome);

    public ArrayList<AnuncioEntity> lerTodosOsAnuncios();
}