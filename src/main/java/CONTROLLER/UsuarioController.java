package CONTROLLER;

import DTO.UsuarioDTO;
import MODEL.UsuarioEntity;
import REPOSITORY.UsuarioRepository;
import SERVICE.Usuario.JWTTokenService;
import SERVICE.Usuario.LoginService;
import SERVICE.Usuario.RegistroService;
import io.javalin.http.Context;

import java.util.Map;

public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final RegistroService registroService;
    private final JWTTokenService jwtTokenService;
    private final LoginService loginService;

    public UsuarioController(
            UsuarioRepository usuarioRepository,
            RegistroService registroService,
            JWTTokenService jwtTokenService,
            LoginService loginService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.registroService = registroService;
        this.jwtTokenService = jwtTokenService;
        this.loginService = loginService;
    }

    public void registrar(Context context){
        UsuarioDTO usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
        registroService.verificarRegistroUsuario(usuarioDTO);
        registroService.registrar(usuarioDTO);
    }

    public void login(Context context){
        UsuarioDTO usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
        loginService.verificarLoginUsuario(usuarioDTO);
        context.json(Map.of("token", loginService.login(usuarioDTO)));
    }

    public void verificarTokenUsuario(Context context){
        Map<String, Object> json = context.bodyAsClass(Map.class);
        String tokenString = (String) json.get(("token"));

        UsuarioEntity usuario = jwtTokenService.verificarUsuarioPorToken(jwtTokenService.decodificarToken(tokenString));
        context.attribute("usuario", usuario);
    }
}
