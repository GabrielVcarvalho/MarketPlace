package service.usuario.contracts;

import service.usuario.Role;

public interface RoleMagenementService {
    boolean isValidRole(String role);

    boolean isAuthorizedRole(String userRole, Role role);
}