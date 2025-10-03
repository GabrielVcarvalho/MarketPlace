package SERVICE;
import MODEL.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
public class Token {
    private static Algorithm algorithm = Algorithm.HMAC256("Password");

    public static String gerarTokenDeUsuario(UsuarioEntity usuario){
        String token = JWT.create()
                .withIssuer("Vitor")
                .withClaim("usuario", usuario.getNome())
                .withClaim("email", usuario.getEmail())
                .withClaim("senha", usuario.getSenha())
                .withClaim("role", usuario.getRole())
                .sign(algorithm);
        return token;
    }
}
