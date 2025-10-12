package CONTROLLER;

import DTO.UsuarioDTO;
import MODEL.UsuarioEntity;
import io.javalin.Javalin;
import java.util.Map;
import SERVICE.RegistroService;
import SERVICE.LoginService;
import SERVICE.TokenService;

public class ApiController {
    public ApiController(){
        Javalin api = Javalin.create();
        api.start(8080);

        api.before("/register", context -> {
            UsuarioDTO usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
            RegistroService registro = new RegistroService();

            registro.verificarRegistroUsuario(usuarioDTO);

            context.attribute("registro", registro);
            context.attribute("usuarioDTO", usuarioDTO);
        });

        api.post("/register", context -> {
            RegistroService registro = context.attribute("registro");
            UsuarioDTO usuarioDTO = context.attribute("usuarioDTO");
            registro.registrar(usuarioDTO);
        });

        api.before("/login", context -> {
            UsuarioDTO usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
            LoginService login = new LoginService();

            login.verificarLoginUsuario(usuarioDTO);

            context.attribute("usuarioDTO", usuarioDTO);
            context.attribute("login", login);
        });

        api.post("/login", context -> {
            LoginService login = context.attribute("login");
            UsuarioDTO usuarioDTO = context.attribute("usuarioDTO");

            context.json(Map.of(login.login(usuarioDTO), "token"));
        });

        api.before("/usuario/*", context -> {
            Map<String, Object> json = context.bodyAsClass(Map.class);
            String tokenString = (String) json.get(("token"));

            TokenService token = new TokenService();
            UsuarioEntity usuario = token.verificarUsuarioByToken(token.decodeToken(tokenString));
            context.attribute("usuario", usuario);
        });
    }
}
