package SERVICE;

import MODEL.UsuarioEntity;

import java.util.HashMap;

public interface TokenService {
    public String criarToken(String nome, String email, String role);

    public HashMap<String, String> decodificarToken(String token);

    public UsuarioEntity verificarUsuarioPorToken(HashMap<String, String> tokenDecodificado);
}
