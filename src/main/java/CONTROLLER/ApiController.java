package CONTROLLER;

import DAO.AnuncioDAO;
import DAO.DeslikeDAO;
import DAO.LikeDAO;
import DAO.UsuarioDAO;
import DTO.AnuncioDTO;
import DTO.UsuarioDTO;
import MODEL.UsuarioEntity;
import REPOSITORY.AnuncioRepository;
import REPOSITORY.UsuarioRepository;
import SERVICE.Anuncio.AnuncioService;
import io.javalin.Javalin;
import java.util.Map;
import SERVICE.Usuario.RegistroService;
import SERVICE.Usuario.LoginService;
import SERVICE.Usuario.JWTTokenService;

public class ApiController {
    public ApiController(){
        Javalin api = Javalin.create();
        api.start(8080);

        UsuarioRepository usuarioRepository = new UsuarioRepository(new UsuarioDAO());
        RegistroService registroService = new RegistroService(usuarioRepository);
        JWTTokenService jwtTokenService = new JWTTokenService("Vitor", usuarioRepository);
        LoginService loginService = new LoginService(usuarioRepository, jwtTokenService);

        AnuncioDAO anuncioDAO = new AnuncioDAO(new LikeDAO(), new DeslikeDAO());
        AnuncioRepository anuncioRepository = new AnuncioRepository(anuncioDAO);
        AnuncioService anuncioService = new AnuncioService(anuncioRepository, usuarioRepository);

        api.post("/register", context -> {
            UsuarioDTO usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
            registroService.verificarRegistroUsuario(usuarioDTO);
            registroService.registrar(usuarioDTO);
        });

        api.post("/login", context -> {
            UsuarioDTO usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
            loginService.verificarLoginUsuario(usuarioDTO);
            context.json(Map.of("token", loginService.login(usuarioDTO)));
        });

        api.before("/usuario/*", context -> {
            Map<String, Object> json = context.bodyAsClass(Map.class);
            String tokenString = (String) json.get(("token"));

            UsuarioEntity usuario = jwtTokenService.verificarUsuarioPorToken(jwtTokenService.decodificarToken(tokenString));
            context.attribute("usuario", usuario);
        });

        api.post("/usuario/vendedor/criarAnuncio", context -> {
           AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);
           anuncioService.verificarCriacaoAnuncio(anuncioDTO);
           anuncioService.criarAnuncio(anuncioDTO);
        });
    }
}
