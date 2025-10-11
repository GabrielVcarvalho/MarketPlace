package CONTROLLER;

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
            RegistroService registro = context.bodyAsClass(RegistroService.class);
            registro.verificarRegistroUsuario();
            context.attribute("registro", registro);
        });

        api.post("/register", context -> {
            RegistroService registro = context.attribute("registro");
            registro.registrar();
        });

        api.before("/login", context -> {
            LoginService login = context.bodyAsClass(LoginService.class);
            login.verificarLoginUsuario();
            context.attribute("login", login);
        });

        api.post("/login", context -> {
            LoginService login = context.attribute("login");
            context.json(Map.of(login.login(), "token"));
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
