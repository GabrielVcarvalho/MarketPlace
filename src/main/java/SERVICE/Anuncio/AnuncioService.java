package SERVICE.Anuncio;

import DTO.AnuncioDTO;
import DTO.UsuarioDTO;
import MODEL.AnuncioEntity;
import MODEL.UsuarioEntity;
import REPOSITORY.AdRepository;
import REPOSITORY.UserRepository;
import SERVICE.Anuncio.Exceptions.NameOfAdAlreadyExists;

public class AnuncioService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public AnuncioService(AdRepository adRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    public void verificarCriacaoAnuncio(UsuarioDTO usuarioDTO, AnuncioDTO anuncioDTO){
        verificarCamposVazios(anuncioDTO.getId(), anuncioDTO.getTitulo(), anuncioDTO.getDescricao());

        if(adRepository.lerAnuncioPeloNome(anuncioDTO.getTitulo()) != null)
            throw new NameOfAdAlreadyExists();
    }

    public void criarAnuncio(AnuncioDTO anuncioDTO){
        adRepository.criarAnuncio(new AnuncioEntity(anuncioDTO.getIdVendedor(),
                anuncioDTO.getTitulo(), anuncioDTO.getDescricao()));
    }

    public void verificarCamposVazios(int id, String titulo, String descricao){

    }
}
