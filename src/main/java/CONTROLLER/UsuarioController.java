package CONTROLLER;

import DTO.UsuarioDTO;
import SERVICE.Usuario.JWTTokenService;
import SERVICE.Usuario.LoginService;
import SERVICE.Usuario.RegistroService;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;

import java.util.Map;

public class UsuarioController {
    private final RegistroService registroService;
    private final JWTTokenService jwtTokenService;
    private final LoginService loginService;

    public UsuarioController(
            RegistroService registroService,
            JWTTokenService jwtTokenService,
            LoginService loginService
    ) {
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
        String tokenHeader =  context.header("Authorization");

        try{
            jwtTokenService.verificarUsuarioPorToken(jwtTokenService.decodificarToken(tokenHeader));
        } catch (RuntimeException e) {
            throw new UnauthorizedResponse();
        }
    }
}