package service;

import java.util.HashMap;

public interface TokenService {
    public String criarToken(String nome, String email, String role);

    public HashMap<String, String> decodificarToken(String token);

    public boolean verificarUsuarioPorToken(HashMap<String, String> tokenDecodificado);
}
