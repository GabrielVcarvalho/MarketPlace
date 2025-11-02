package service.anuncio;

import dto.AnuncioDTO;
import dto.UsuarioDTO;
import model.AnuncioEntity;
import model.UsuarioEntity;
import repository.contracts.AdRepository;
import repository.contracts.UserRepository;
import dto.DTOUtils;
import service.anuncio.exceptions.AnuncioIdNotExists;
import service.anuncio.exceptions.InvalidSellerId;
import service.anuncio.exceptions.TitleOfAdAlreadyExists;
import service.anuncio.exceptions.TitleOfAdNotExits;
import service.exceptions.NullDTO;
import service.mapper.AnuncioMapper;
import service.mapper.UsuarioMapper;
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
    private final AnuncioMapper anuncioMapper = new AnuncioMapper();

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
        UsuarioMapper usuarioMapper = new UsuarioMapper();

        verificarAnuncioDTO(anuncioDTO);

        /*
        A ideia aqui era fazer com que a camada SERVICE não precise trabalhar com entities;
        Tornando o código mais padronizado e seguindo um modelo mais uniforme, para que não
        haja confusão
         */
        UsuarioDTO usuario;
        try{
            //Encapsulado dentro do bloco try para que não vaze para o resto do programa
            UsuarioEntity usuarioOutRepository = userRepository.lerUsuarioPorId(UsuarioEntity.buildWithId(anuncioDTO.getIdVendedor()));
            usuario = usuarioMapper.convertToDTO(usuarioOutRepository);
        }catch (NullMapperObject e){
            throw new InvalidSellerId();
        }

        if(!roleMagenementService.isAuthorizedRole(usuario.getRole(), Role.VENDEDOR))
            throw new UnauthorizedRole("Role não autorizada");

        if(adRepository.lerAnuncioPeloNome(anuncioDTO.getTitulo().toUpperCase()) != null)
            throw new TitleOfAdAlreadyExists();
    }

    public void criarAnuncio(AnuncioDTO anuncioDTO){
        adRepository.criarAnuncio(anuncioMapper.convertToEntity());
    }

    public AnuncioDTO lerAnuncio(String titulo){
        if(titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("Argumento de título vazio");

        AnuncioEntity anuncio = adRepository.lerAnuncioPeloNome(titulo);

        if(anuncio == null)
            throw new AnuncioIdNotExists("O anuncio informado não existe");

        return toDTO(anuncio);
    }

    public AnuncioDTO lerAnuncio(int id){
        if(id <= 0)
            throw new IllegalArgumentException("Argumento de id é nulo ou inválido");

        AnuncioEntity anuncio = adRepository.lerAnuncioPeloId(id);

        if(anuncio == null)
            throw new TitleOfAdNotExits("Não foi possível encontrar anúncio com esse nome");

        return toDTO(anuncio);
    }

    public ArrayList<AnuncioDTO> lerTodosOsAnuncios(){
        return adRepository.lerTodosOsAnuncios()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toCollection(ArrayList<AnuncioDTO>::new));
    }

    /*
    Vou deixar esse método aqui por causa da verificação segura do "verificarCamposVazios"
    Para evitar a introdução de bugs
     */
    private AnuncioDTO toDTO(AnuncioEntity from){
        verificarCamposVazios(from.getId(),
                from.getTitulo(),
                from.getDescricao());

        return anuncioMapper.convertToDTO(from);
    }

    private void verificarAnuncioDTO(AnuncioDTO anuncioDTO){
        if(DTOUtils.isNull(anuncioDTO))
            throw new NullDTO("O corpo da requisição está vazio");

        verificarCamposVazios(
                anuncioDTO.getId(),
                anuncioDTO.getTitulo(),
                anuncioDTO.getDescricao());
    }

    private void verificarCamposVazios(int id, String titulo, String descricao){
        if(id <= 0 ||
                titulo == null || titulo.isBlank() ||
                descricao == null || descricao.isBlank())
            throw new IllegalArgumentException("Campo vazio ou inválido na criação do anuncio");
    }
}