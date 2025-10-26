package SERVICE.Anuncio;

import DTO.AnuncioDTO;
import MODEL.AnuncioEntity;
import REPOSITORY.AdRepository;
import REPOSITORY.UserRepository;
import SERVICE.Anuncio.Exceptions.AnuncioIdNotExists;
import SERVICE.Anuncio.Exceptions.InvalidSellerId;
import SERVICE.Anuncio.Exceptions.TitleOfAdAlreadyExists;
import SERVICE.Anuncio.Exceptions.TitleOfAdNotExits;

public class AnuncioService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public AnuncioService(
            AdRepository adRepository,
            UserRepository userRepository
    ) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    public void verificarCriacaoAnuncio(AnuncioDTO anuncioDTO){
        verificarCamposVazios(anuncioDTO.getIdVendedor(), anuncioDTO.getTitulo(), anuncioDTO.getDescricao());

        if(userRepository.lerUsuarioPorId(anuncioDTO.getIdVendedor()) == null)
            throw new InvalidSellerId();

        if(adRepository.lerAnuncioPeloNome(anuncioDTO.toEntity().getTitulo()) != null)
            throw new TitleOfAdAlreadyExists();
    }

    public void criarAnuncio(AnuncioDTO anuncioDTO){
        adRepository.criarAnuncio(anuncioDTO.toEntity());
    }

    public AnuncioDTO lerAnuncio(String titulo){
        if(titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("Argumento de título vazio");

        AnuncioEntity anuncio = adRepository.lerAnuncioPeloNome(titulo);

        if(anuncio == null)
            throw new TitleOfAdNotExits("O anuncio informado não existe");

        return toDTO(anuncio);
    }

    public AnuncioDTO lerAnuncio(int id){
        if(id <= 0)
            throw new IllegalArgumentException("Argumento de id é nulo ou inválido");

        AnuncioEntity anuncio = adRepository.lerAnuncioPeloId(id);

        if(anuncio == null)
            throw new AnuncioIdNotExists("O anuncio informado não existe");

        return toDTO(anuncio);
    }

    //Tirado de dentro da entity para não dar responsibilidades demais a parte mais interna do código
    private AnuncioDTO toDTO(AnuncioEntity from){
        verificarCamposVazios(from.getId(),
                from.getTitulo(),
                from.getDescricao());


        return new AnuncioDTO(
                from.getId(),
                from.getIdVendedor(),
                from.getTitulo(),
                from.getDescricao(),
                from.getLikes(),
                from.getDeslikes());
    }

    private void verificarCamposVazios(int id, String titulo, String descricao){
        if(id <= 0 ||
                titulo == null || titulo.isBlank() ||
                descricao == null || descricao.isBlank())
            throw new IllegalArgumentException("Campo vazio ou inválido na criação do anuncio");
    }
}