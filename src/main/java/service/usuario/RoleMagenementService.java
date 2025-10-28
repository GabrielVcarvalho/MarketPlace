package service.usuario;

public interface RoleMagenementService {
    public boolean isValidRole(String role);

    public boolean isAuthorizedRole(String userRole, Role role);
}