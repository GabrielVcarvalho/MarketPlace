package SERVICE.Anuncio;

import DTO.AnuncioDTO;
import REPOSITORY.AdRepository;
import REPOSITORY.UserRepository;
import SERVICE.Anuncio.Exceptions.InvalidSellerId;
import SERVICE.Anuncio.Exceptions.TitleOfAdAlreadyExists;

public class AnuncioService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public AnuncioService(AdRepository adRepository, UserRepository userRepository) {
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

    private void verificarCamposVazios(int id, String titulo, String descricao){
        if(id <= 0 ||
                titulo == null || titulo.isBlank() ||
                descricao == null || descricao.isBlank())
            throw new IllegalArgumentException("Campo vazio ou inválido na criação do anuncio");
    }
}