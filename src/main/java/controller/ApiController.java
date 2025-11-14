package controller;

import dao.AnuncioDAO;
import dao.DeslikeDAO;
import dao.LikeDAO;
import dao.UsuarioDAO;
import repository.AnuncioRepository;
import repository.AvaliacaoAnuncioRepository;
import repository.UsuarioRepository;
import service.anuncio.AnuncioService;
import service.anuncio.ComentarioService;
import service.anuncio.FeedBackService;
import io.javalin.Javalin;
import service.mapper.AnuncioMapper;
import service.mapper.UsuarioMapper;
import service.usuario.*;

public class ApiController {
    public ApiController(){
        Javalin api = Javalin.create();

        UsuarioRepository usuarioRepository = new UsuarioRepository(new UsuarioDAO());
        JWTTokenService jwtTokenService = new JWTTokenService("Vitor",  usuarioRepository);
        RoleService roleService = new RoleService();
        UsuarioMapper usuarioMapper = new UsuarioMapper();
        RegistroService registroService = new RegistroService(
                usuarioRepository,
                roleService,
                usuarioMapper);
        LoginService loginService = new LoginService(usuarioRepository, jwtTokenService);
        LeituraService leituraService = new LeituraService(
                usuarioRepository,
                roleService,
                usuarioMapper);

        LikeDAO likeDAO = new LikeDAO();
        DeslikeDAO deslikeDAO = new DeslikeDAO();
        AnuncioDAO anuncioDAO = new AnuncioDAO(likeDAO, deslikeDAO);
        AnuncioMapper anuncioMapper = new AnuncioMapper();
        AnuncioRepository anuncioRepository = new AnuncioRepository(
                anuncioDAO,
                likeDAO,
                deslikeDAO
        );
        AnuncioService anuncioService = new AnuncioService(
                anuncioRepository,
                usuarioRepository,
                roleService,
                anuncioMapper
        );
        FeedBackService feedBackService = new FeedBackService(
                new AvaliacaoAnuncioRepository(likeDAO, deslikeDAO),
                anuncioRepository,
                usuarioRepository
        );

        UsuarioController usuarioController = new UsuarioController(
                registroService,
                jwtTokenService,
                loginService,
                leituraService
        );

        AnuncioController anuncioController = new AnuncioController(
                anuncioService,
                feedBackService
        );

        api.post("/register", usuarioController::registrar);

        api.post("/login", usuarioController::login);

        api.before("/usuario/*", usuarioController::verificarTokenUsuario);

        api.post("/usuario/vendedor/criarAnuncio", anuncioController::criarAnuncio);

        api.post("/usuario/lerTodosOsUsuarios", usuarioController::lerTodosOsUsuarios);

        api.get("/usuario/lerTodosOsAnuncio", anuncioController::lerTodosOsAnuncios);

        api.get("usuario/buscarAnuncioPorId", anuncioController::lerAnuncioPeloId);

        api.get("/usuario/buscarAnuncioPorTitulo", anuncioController::lerAnuncioPeloTitulo);

        api.post("/usuario/likeAnuncio", anuncioController::likeAnuncio);

        api.post("/usuario/deslikeAnuncio", anuncioController::deslikeAnuncio);

        api.start(8080);
    }
}