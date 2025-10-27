package service.Usuario;

import service.Exceptions.InvalidRole;

import java.util.Arrays;

public class RoleService implements RoleMagenementService{

    @Override
    public boolean isValidRole(String role){
        if(role == null || role.isBlank())
            throw new InvalidRole();

         return Arrays.stream(Role.values()).anyMatch(r -> r.name().equalsIgnoreCase(role));
    }
}