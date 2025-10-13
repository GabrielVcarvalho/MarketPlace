package CONTROLLER;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import MODEL.UsuarioEntity;
import REPOSITORY.UsuarioRepository;
import io.javalin.Javalin;
import java.util.Map;
import SERVICE.RegistroService;
import SERVICE.LoginService;
import SERVICE.TokenService;

public class ApiController {
    public ApiController(){
        Javalin api = Javalin.create();
        api.start(8080);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioRepository usuarioRepository = new UsuarioRepository(usuarioDAO);
        RegistroService registroService = new RegistroService(usuarioRepository);
        TokenService tokenService = new TokenService("Vitor", usuarioRepository);
        LoginService loginService = new LoginService(usuarioRepository, tokenService);

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

            UsuarioEntity usuario = tokenService.verificarUsuarioByToken(tokenService.decodeToken(tokenString));
            context.attribute("usuario", usuario);
        });
    }
}
