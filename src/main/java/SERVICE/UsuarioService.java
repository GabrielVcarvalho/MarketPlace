package SERVICE;

import DAO.QueryTemplate;
import MODEL.UsuarioEntity;
import SERVICE.Exceptions.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.HashMap;

public class UsuarioService {
    public static class Registro{
        private String nome;
        private String email;
        private String senha;
        private String role;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public void registrar(){
            QueryTemplate.Usuario.createUsuario(new UsuarioEntity(nome,
                    email, senha, role));
        }

        public void verificarRegistroUsuario(){
            verificacaoUtils.verificarCamposVazios(nome, email, senha);
            if (!verificacaoUtils.isValidRole(role)) throw new InvalidRole();
            if (verificacaoUtils.nameUserAlredyExist(nome)) throw new NameUserAlredyExists();
            if (verificacaoUtils.emailUserAlredyExist(email)) throw new EmailUserAlredyUsed();
        }
    }

    public static class Login{
        private String nome;
        private String email;
        private String senha;
        private String role;

        public String getNome() {
            return nome;
        }

        public String getEmail() {
            return email;
        }

        public String getSenha() {
            return senha;
        }

        public String getRole() {
            return role;
        }

        public Login(){

        }

        public String login(){
            return new Token("Vitor").createToken(nome, email, role);
        }

        public boolean verificarLoginUsuario(){
            verificacaoUtils.verificarCamposVazios(nome, email, senha);
            if (!verificacaoUtils.nameUserAlredyExist(nome)) throw new NameUserNotExists();
            if (!verificacaoUtils.emailUserAlredyExist(email)) throw new EmailNotInUse();
            if (!isCorrectPassword()) throw new WrongPassword();
            return true;
        }

        private boolean isCorrectPassword(){
            return QueryTemplate.Usuario.readUsuarioByName(nome).getSenha().equals(senha);
        }
    }

    public static class Token {
        private static final Algorithm algorithm = Algorithm.HMAC256("password");
        private String issuer;

        public Algorithm getAlgorithm() {
            return algorithm;
        }

        public String getIssuer() {
            return issuer;
        }

        public Token(String issuer) {
            this.issuer = issuer;
        }

        public Token() {

        }

        public String createToken(String nome, String email, String role){
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
    }

    private static class verificacaoUtils{
        private static boolean verificarCamposVazios(String nome,String email, String senha){
            if (nome == null || nome.isEmpty()) throw new EmptyName();
            if (email == null || email.isEmpty()) throw new EmptyEmail();
            if (senha == null || senha.isEmpty()) throw new EmptyPassword();
            return true;
        }

        private static boolean nameUserAlredyExist(String nome){
            return QueryTemplate.Usuario.readUsuarioByName(nome) != null;
        }

        private static boolean emailUserAlredyExist(String email){
            return QueryTemplate.Usuario.readUsuarioByEmail(email) != null;
        }

        private static boolean isValidRole(String role){
            if(role != null){
                return role.equalsIgnoreCase("cliente")
                        || role.equalsIgnoreCase("vendedor");
            }else return false;
        }
    }
}