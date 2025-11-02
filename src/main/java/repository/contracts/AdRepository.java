package repository.contracts;

import model.AnuncioEntity;

import java.util.ArrayList;

public interface AdRepository {
    public void criarAnuncio(AnuncioEntity anuncio);

    public void removerAnuncio(AnuncioEntity anuncio);

    public AnuncioEntity lerAnuncioPeloId(AnuncioEntity anuncio);

    public AnuncioEntity lerAnuncioPeloNome(AnuncioEntity anuncio);

    public ArrayList<AnuncioEntity> lerTodosOsAnuncios();
}