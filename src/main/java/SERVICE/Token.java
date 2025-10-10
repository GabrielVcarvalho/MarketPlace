package SERVICE;
import MODEL.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.HashMap;

public class Token {
    private static Algorithm algorithm = Algorithm.HMAC256("Password");

    public static String gerarTokenDeUsuario(String nome, String email, String role){
        String token = JWT.create()
                .withIssuer("Vitor")
                .withClaim("usuario", nome)
                .withClaim("email", email)
                .withClaim("role", role)
                .sign(algorithm);
        return token;
    }

    public static HashMap<String, String> interpretarTokenUsuario(String token){
        HashMap<String, String> camposUsuario = new HashMap<>();

        try{
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        camposUsuario.put("nome", jwt.getClaim("nome").asString());
        camposUsuario.put("email", jwt.getClaim("email").asString());
        camposUsuario.put("role", jwt.getClaim("role").asString());
        return  camposUsuario;
        }catch (JWTVerificationException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
