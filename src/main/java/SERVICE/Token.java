package SERVICE;
import MODEL.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
}
