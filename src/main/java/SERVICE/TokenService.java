package SERVICE;

import MODEL.UsuarioEntity;
import REPOSITORY.UserRepository;
import SERVICE.Exceptions.InvalidUserToken;
import SERVICE.Exceptions.NameUserNotExists;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.util.HashMap;

public class TokenService {
    private static final Algorithm algorithm = Algorithm.HMAC256("password");
    private String issuer;
    private final UserRepository userRepository;

    public TokenService(String issuer, UserRepository userRepository) {
        this.issuer = issuer;
        this.userRepository = userRepository;
    }

    public String createToken(String nome, String email, String role){
        VerificacaoUtils.verificarCamposVazios(nome, email, role);
        String token = JWT.create()
                .withIssuer(issuer)
                .withClaim("nome", nome)
                .withClaim("email", email)
                .withClaim("role", role)
                .sign(algorithm);
        return token;
    }

    public HashMap<String, String> decodeToken(String token){
        HashMap<String, String> camposUsuario = new HashMap<>();

        try{
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
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

    public UsuarioEntity verificarUsuarioByToken(HashMap<String, String> jwtDecodificado){
        UsuarioEntity usuario;
        String nome = jwtDecodificado.get("nome");
        String email = jwtDecodificado.get("email");
        String role = jwtDecodificado.get("role");

        VerificacaoUtils.verificarCamposVazios(nome, email, role);
        usuario = userRepository.lerUsuarioPorNome(nome);
        if(usuario == null) throw new NameUserNotExists();
        if(!usuario.getNome().equalsIgnoreCase(nome)
                || !usuario.getEmail().equalsIgnoreCase(email)
                || !usuario.getRole().equalsIgnoreCase(role)) throw new InvalidUserToken();
        return new UsuarioEntity(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getRole());
    }
}