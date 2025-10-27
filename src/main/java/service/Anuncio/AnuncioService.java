package service.Anuncio;

import dto.AnuncioDTO;
import model.AnuncioEntity;
import model.UsuarioEntity;
import repository.AdRepository;
import repository.UserRepository;
import service.Anuncio.Exceptions.AnuncioIdNotExists;
import service.Anuncio.Exceptions.InvalidSellerId;
import service.Anuncio.Exceptions.TitleOfAdAlreadyExists;
import service.Anuncio.Exceptions.TitleOfAdNotExits;
import service.Usuario.Exceptions.UnauthorizedRole;
import service.Usuario.Role;
import service.Usuario.RoleMagenementService;
import service.Usuario.RoleService;

public class AnuncioService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final RoleMagenementService roleMagenementService;

    public AnuncioService(
            AdRepository adRepository,
            UserRepository userRepository,
            RoleMagenementService roleMagenementService
    ) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.roleMagenementService = roleMagenementService;
    }

    public void verificarCriacaoAnuncio(AnuncioDTO anuncioDTO){
        verificarCamposVazios(anuncioDTO.getIdVendedor(), anuncioDTO.getTitulo(), anuncioDTO.getDescricao());

        UsuarioEntity usuario = userRepository.lerUsuarioPorId(anuncioDTO.getIdVendedor());

        if(usuario == null)
            throw new InvalidSellerId();

        if(!roleMagenementService.isAuthorizedRole(usuario.getRole(), Role.VENDEDOR))
            throw new UnauthorizedRole("Role não autorizada");

        if(adRepository.lerAnuncioPeloNome(anuncioDTO.toEntity().getTitulo()) != null)
            throw new TitleOfAdAlreadyExists();
    }

    public void criarAnuncio(AnuncioDTO anuncioDTO){
        adRepository.criarAnuncio(anuncioDTO.toEntity());
    }

    public AnuncioDTO lerAnuncio(String titulo){
        if(titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("Argumento de título vazio");

        AnuncioEntity anuncio = adRepository.lerAnuncioPeloNome(titulo.toUpperCase());

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