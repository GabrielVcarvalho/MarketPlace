package CONTROLLER;

import DAO.AnuncioDAO;
import DAO.DeslikeDAO;
import DAO.LikeDAO;
import DAO.UsuarioDAO;
import DTO.AnuncioDTO;
import REPOSITORY.AnuncioRepository;
import REPOSITORY.UsuarioRepository;
import SERVICE.Anuncio.AnuncioService;
import io.javalin.Javalin;
import SERVICE.Usuario.RegistroService;
import SERVICE.Usuario.LoginService;
import SERVICE.Usuario.JWTTokenService;

public class ApiController {
    public ApiController(){
        Javalin api = Javalin.create();
        api.start(8080);

        UsuarioRepository usuarioRepository = new UsuarioRepository(new UsuarioDAO());
        JWTTokenService jwtTokenService = new JWTTokenService("Vitor",  usuarioRepository);
        RegistroService registroService = new RegistroService(usuarioRepository);
        LoginService loginService = new LoginService(usuarioRepository, jwtTokenService);

        AnuncioDAO anuncioDAO = new AnuncioDAO(new LikeDAO(), new DeslikeDAO());
        AnuncioRepository anuncioRepository = new AnuncioRepository(anuncioDAO);
        AnuncioService anuncioService = new AnuncioService(anuncioRepository, usuarioRepository);

        UsuarioController usuarioController = new UsuarioController(
                usuarioRepository, registroService,
                jwtTokenService, loginService
        );

        AnuncioController anuncioController = new AnuncioController(
                anuncioDAO, anuncioRepository, anuncioService);

        api.post("/register", usuarioController::registrar);

        api.post("/login", usuarioController::login);

        api.before("/usuario/*", usuarioController::verificarTokenUsuario);

        api.post("/usuario/vendedor/criarAnuncio", anuncioController::criarAnuncio);
    }
}