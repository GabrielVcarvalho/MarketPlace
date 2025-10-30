package controller;

import dto.UsuarioDTO;
import service.exceptions.NullDTO;
import service.usuario.JWTTokenService;
import service.usuario.LeituraService;
import service.usuario.LoginService;
import service.usuario.RegistroService;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import service.usuario.exceptions.*;

import java.util.Map;

public class UsuarioController {
    private final RegistroService registroService;
    private final JWTTokenService jwtTokenService;
    private final LoginService loginService;
    private final LeituraService leituraService;

    UsuarioController(
            RegistroService registroService,
            JWTTokenService jwtTokenService,
            LoginService loginService,
            LeituraService leituraService
    ) {
        this.registroService = registroService;
        this.jwtTokenService = jwtTokenService;
        this.loginService = loginService;
        this.leituraService = leituraService;
    }

    //Vou quebrar mais os catchs para melhorar a legibilidade
    void registrar(Context context){
        UsuarioDTO usuarioDTO;

        try{
            usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
            registroService.verificarRegistroUsuario(usuarioDTO);
            registroService.registrar(usuarioDTO);
            context.status(201);
        }
        catch (NullDTO e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (EmptyName | EmptyEmail | EmptyPassword e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (InvalidRole e){
            context.status(403)
                    .result(e.getMessage());
        }
        catch (NameUserAlreadyExists | EmailUserAlreadyUsed e){
            context.status(409)
                    .result(e.getMessage());
        }
    }

    void login(Context context){
        UsuarioDTO usuarioDTO;

        try{
            usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
            loginService.verificarLoginUsuario(usuarioDTO);
            context.json(Map.of("token", loginService.login(usuarioDTO)));
            context.status(200);
        }
        catch (NullDTO e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (EmptyName | EmptyEmail | EmptyPassword e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (InvalidRole e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (NameUserNotExists | EmailNotInUse e){
            context.status(404)
                    .result(e.getMessage());
        }
        catch (InvalidUserToken | WrongPassword  e){
            throw new UnauthorizedResponse();
        }
    }

    void verificarTokenUsuario(Context context){
        String tokenHeader =  context.header("Authorization");

        try{
            jwtTokenService.verificarUsuarioPorToken(jwtTokenService.decodificarToken(tokenHeader));
        }
        catch (EmptyName | EmptyEmail | EmptyPassword e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (NameUserNotExists e){
            context.status(404)
                    .result(e.getMessage());
        }
        catch (InvalidUserToken e){
            throw new UnauthorizedResponse();
        }
    }

    void lerTodosOsUsuarios(Context context){
        try{
            UsuarioDTO usuarioDTO = context.bodyAsClass(UsuarioDTO.class);
            context.json(leituraService.lerTodosOsUsuarios(usuarioDTO));
            context.status(200);
        }catch (NullDTO e){
            context.status(400)
                    .result(e.getMessage());
        }catch (UnauthorizedRole e){
            throw new UnauthorizedResponse(e.getMessage());
        }
    }
}