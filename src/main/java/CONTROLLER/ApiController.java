package CONTROLLER;

import SERVICE.UsuarioService;
import SERVICE.UsuarioService.*;
import io.javalin.Javalin;
import java.util.Map;

public class ApiController {
    public ApiController(){
        Javalin api = Javalin.create();
        api.start(8080);

        api.before("/register", context -> {
            UsuarioService.Registro registro = context.bodyAsClass(Registro.class);
            registro.verificarRegistroUsuario();
            context.attribute("registro", registro);
        });

        api.post("/register", context -> {
            UsuarioService.Registro registro = context.attribute("registro");
            registro.registrar();
        });

        api.before("/login", context -> {
            UsuarioService.Login login = context.bodyAsClass(Login.class);
            login.verificarLoginUsuario();
            context.attribute("login", login);
        });

        api.post("/login", context -> {
            UsuarioService.Login login = context.attribute("login");
            context.json(Map.of(login.login(), "token"));
        });

        api.before("/usuario/*", context -> {
            Map<String, Object> json = context.bodyAsClass(Map.class);
            String tokenString = (String) json.get(("token"));

            UsuarioService.Token token = new Token();
            token.verificarUsuarioByToken(token.decodeToken(tokenString));
        });
    }
}
