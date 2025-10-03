package CONTROLLER;

import MODEL.UsuarioEntity;
import SERVICE.UsuarioService;
import io.javalin.Javalin;

public class ApiController {
    public ApiController(){
        Javalin api = Javalin.create();
        api.start(8080);
        api.before("/users/register", context -> {
            UsuarioEntity usuario = context.bodyAsClass(UsuarioEntity.class);
            UsuarioService.Registro.verificarRegistroUsuario(usuario);
        });

        api.post("/users/register", context -> {
            UsuarioEntity usuario = context.bodyAsClass(UsuarioEntity.class);
            UsuarioService.Registro.registrar(usuario);
        });
    }
}
