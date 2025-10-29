package controller;

import dto.UsuarioDTO;
import service.usuario.JWTTokenService;
import service.usuario.LoginService;
import service.usuario.RegistroService;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;

import java.util.Map;

public class UsuarioController {
    private final RegistroService registroService;
    private final JWTTokenService jwtTokenService;
    private final LoginService loginService;

    UsuarioController(
            RegistroService registroService,
            JWTTokenService jwtTokenService,
            LoginService loginService
    ) {
        this.registroService = registroService;
        this.jwtTokenService = jwtTokenService;
        this.loginService = loginService;
    }

    void registrar(Context context){
        UsuarioDTO usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
        registroService.verificarRegistroUsuario(usuarioDTO);
        registroService.registrar(usuarioDTO);
        context.status(201);
    }

    void login(Context context){
        UsuarioDTO usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
        loginService.verificarLoginUsuario(usuarioDTO);
        context.json(Map.of("token", loginService.login(usuarioDTO)));
    }

    void verificarTokenUsuario(Context context){
        String tokenHeader =  context.header("Authorization");

        try{
            jwtTokenService.verificarUsuarioPorToken(jwtTokenService.decodificarToken(tokenHeader));
        } catch (RuntimeException e) {
            throw new UnauthorizedResponse();
        }
    }
}