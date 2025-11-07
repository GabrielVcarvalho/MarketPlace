package service.anuncio;

import dto.AnuncioDTO;
import model.AnuncioEntity;
import model.UsuarioEntity;
import service.mapper.contracts.AdMapper;
import repository.contracts.AdRepository;
import repository.contracts.UserRepository;
import dto.DTOUtils;
import service.anuncio.exceptions.*;
import service.exceptions.NullDTO;
import service.mapper.exceptions.NullMapperObject;
import service.usuario.exceptions.UnauthorizedRole;
import service.usuario.Role;
import service.usuario.contracts.RoleMagenementService;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AnuncioService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final RoleMagenementService roleMagenementService;
    private final AdMapper adMapper;

    public AnuncioService(
            AdRepository adRepository,
            UserRepository userRepository,
            RoleMagenementService roleMagenementService,
            AdMapper adMapper
    ) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.roleMagenementService = roleMagenementService;
        this.adMapper = adMapper;
    }

    public void verificarCriacaoAnuncio(AnuncioDTO anuncioDTO){
        verificarDTONulo(anuncioDTO);

        UsuarioEntity usuario;
        AnuncioEntity adByRepository;

        usuario = userRepository.lerUsuarioPorId(anuncioDTO.getIdVendedor());

        if(usuario == null)
            throw new InvalidSellerId();

        if(!roleMagenementService.isAuthorizedRole(usuario.getRole(), Role.VENDEDOR))
            throw new UnauthorizedRole("Role não autorizada");

        adByRepository = adRepository.lerAnuncioPeloNome(anuncioDTO.getTitulo());

        if(adByRepository != null)
            throw new TitleOfAdAlreadyExists();
    }

    public void criarAnuncio(AnuncioDTO anuncioDTO){
        verificarCamposVazios(
                anuncioDTO.getId(),
                anuncioDTO.getTitulo(),
                anuncioDTO.getDescricao());

        try{
            adRepository.criarAnuncio(adMapper.convertToEntity(anuncioDTO));
        }
        catch (NullMapperObject e) {
            throw new NullDTO("O corpo da requisição é nulo ou inválido");
        }
    }

    public AnuncioDTO lerAnuncioPeloTitulo(AnuncioDTO anuncioDTO){
        if(anuncioDTO.getTitulo() == null || anuncioDTO.getTitulo().isBlank())
            throw new IllegalArgumentException("Argumento de título vazio");

        try{
            AnuncioEntity anuncioOutRepository = adRepository.lerAnuncioPeloNome(anuncioDTO.getTitulo());
            return toDTO(anuncioOutRepository);
        }
        catch (NullMapperObject e){
            throw new TitleOfAdNotExits("Não foi possível encontrar anuncio com esse título");
        }
    }

    public AnuncioDTO lerAnuncioPeloId(AnuncioDTO anuncioDTO){
        if(anuncioDTO.getId() <= 0)
            throw new IllegalArgumentException("Argumento de id é nulo ou inválido");

        try {
            AnuncioEntity anuncioOutRepository = adRepository.lerAnuncioPeloId(anuncioDTO.getId());
            return toDTO(anuncioOutRepository);
        }
        catch (NullMapperObject e) {
            throw new AnuncioIdNotExists("Não foi possível encontrar um anuncio com esse id");
        }
    }

    public ArrayList<AnuncioDTO> lerTodosOsAnuncios() throws InternalErrorToConvertToDTO {
            return adRepository.lerTodosOsAnuncios()
            .stream()
            .map((anuncio) -> {
                try {
                    return toDTO(anuncio);
                }
                catch (NullMapperObject e){
                    //Erro desconhecido
                    throw new InternalErrorToConvertToDTO("Erro interno não esperado");
                }
            })
            .collect(Collectors.toCollection(ArrayList<AnuncioDTO>::new));
        }

    private AnuncioDTO toDTO(AnuncioEntity from) throws NullMapperObject{
        return adMapper.convertToDTO(from);
    }

    private void verificarDTONulo(AnuncioDTO anuncioDTO){
        if(DTOUtils.isNull(anuncioDTO))
            throw new NullDTO("O corpo da requisição está vazio");
    }

    private void verificarCamposVazios(int id, String titulo, String descricao){
        if(id <= 0 ||
                titulo == null || titulo.isBlank() ||
                descricao == null || descricao.isBlank())
            throw new IllegalArgumentException("Campo vazio ou inválido na criação do anuncio");
    }
}