package service.Usuario;

public interface RoleMagenementService {
    public boolean isValidRole(String role);

    public boolean isAuthorizedRole(String userRole, Role role);
}