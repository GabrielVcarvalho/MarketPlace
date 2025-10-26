package CONTROLLER;

import DAO.AnuncioDAO;
import DAO.DeslikeDAO;
import DAO.LikeDAO;
import DAO.UsuarioDAO;
import REPOSITORY.AnuncioRepository;
import REPOSITORY.AvaliacaoAnuncioRepository;
import REPOSITORY.UsuarioRepository;
import SERVICE.Anuncio.AnuncioService;
import SERVICE.Anuncio.FeedBackService;
import io.javalin.Javalin;
import SERVICE.Usuario.RegistroService;
import SERVICE.Usuario.LoginService;
import SERVICE.Usuario.JWTTokenService;

public class ApiController {
    public ApiController(){
        Javalin api = Javalin.create();

        UsuarioRepository usuarioRepository = new UsuarioRepository(new UsuarioDAO());
        JWTTokenService jwtTokenService = new JWTTokenService("Vitor",  usuarioRepository);
        RegistroService registroService = new RegistroService(usuarioRepository);
        LoginService loginService = new LoginService(usuarioRepository, jwtTokenService);

        LikeDAO likeDAO = new LikeDAO();
        DeslikeDAO deslikeDAO = new DeslikeDAO();
        AnuncioDAO anuncioDAO = new AnuncioDAO(likeDAO, deslikeDAO);
        AnuncioRepository anuncioRepository = new AnuncioRepository(
                anuncioDAO,
                likeDAO, deslikeDAO);
        AnuncioService anuncioService = new AnuncioService(anuncioRepository, usuarioRepository);
        FeedBackService feedBackService = new FeedBackService(
                new AvaliacaoAnuncioRepository(likeDAO, deslikeDAO),
                anuncioRepository,
                usuarioRepository
        );

        UsuarioController usuarioController = new UsuarioController(
                registroService,
                jwtTokenService,
                loginService
        );

        AnuncioController anuncioController = new AnuncioController(
                anuncioService,
                feedBackService
        );

        api.post("/register", usuarioController::registrar);

        api.post("/login", usuarioController::login);

        api.before("/usuario/*", usuarioController::verificarTokenUsuario);

        api.post("/usuario/vendedor/criarAnuncio", anuncioController::criarAnuncio);

        api.get("usuario/buscarAnuncioPorId/{id}", anuncioController::lerAnuncioPeloId);

        api.get("/usuario/buscarAnuncioPorTitulo/{titulo}", anuncioController::lerAnuncioPeloTitulo);

        api.post("/usuario/likeAnuncio", anuncioController::likeAnuncio);

        api.post("/usuario/deslikeAnuncio", anuncioController::deslikeAnuncio);

        api.start(8080);
    }
}