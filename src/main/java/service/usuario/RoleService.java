package service.usuario;

import service.usuario.exceptions.InvalidRole;

import java.util.Arrays;

public class RoleService implements RoleMagenementService{

    @Override
    public boolean isValidRole(String role){
        verifyNullOrEmptyRole(role);
         return Arrays.stream(Role.values()).anyMatch(r -> r.name().equalsIgnoreCase(role));
    }

    @Override
    public boolean isAuthorizedRole(String userRole, Role validRole) {
        verifyNullOrEmptyRole(userRole);
        return userRole.equalsIgnoreCase(validRole.name());
    }

    private void verifyNullOrEmptyRole(String role){
        if(role == null || role.isBlank())
            throw new InvalidRole();
    }
}