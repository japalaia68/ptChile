package pt.security.config;


import pt.config.core.CoreProperties;
import pt.exception.CustomDaoException;
import pt.exception.CustomServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Value("${custom.chequeo-seguridad:true}")
    private Boolean chequeoSeguridad;



    @Autowired
    private CoreProperties coreProperties = new CoreProperties();

    @Override
    public boolean hasPermission(
            Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();

        try {
            return hasPrivilege(auth, targetType, permission.toString().toUpperCase());
        } catch (CustomDaoException e) {
            e.printStackTrace();
        } catch (CustomServiceException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Esta funcion permite validar un registro en particular
    @Override
    public boolean hasPermission(
            Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        try {
            return hasPrivilege(auth, targetType.toUpperCase(),
                    permission.toString().toUpperCase());
        } catch (CustomDaoException e) {
            e.printStackTrace();
        } catch (CustomServiceException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean hasPrivilege(Authentication auth, String targetType, String permission) throws AccessDeniedException, CustomDaoException, CustomServiceException {
        try {
            if (!chequeoSeguridad) // si esta desabilitado el chequeo de seguirdad, la respuesta es que siempre tiene permisos
                return true;

            if (permission.equalsIgnoreCase("ANONIMO")) {
                return true;
            }
        //    Rol rol = null;
            List<String> nombreRoles = new ArrayList<>();
            for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
                if (!grantedAuth.getAuthority().equalsIgnoreCase("ROLE_ANONYMOUS")) {//Si es anonimo-->no logeado
//                    rol = (Rol) grantedAuth;
//                    nombreRoles.add(rol.getNombre());
//                    if (rol != null && rol.getPermisos() != null && !rol.getPermisos().isEmpty()) {
//                        for (Permiso permiso : rol.getPermisos()) {
//                            if (permiso.getNombre().toUpperCase().contains(permission.toUpperCase())) {
//                                return true;
//                            }
//                        }
//
//                    }
                } else {
                    return false;
                }
            }
            // si recorrio todos los permisos y resulta que el usuario no tiene el permiso, chequeo que exista el permiso, si no esta, se crea y se asigna al rol administrador
     //       rolService.addAndCreatePermisoToAdmin(permission.toUpperCase());
            //Si es administrador, entoces no se chequea la seguridad
            if (nombreRoles.contains(coreProperties.getRolAdministrador()))
                return true;
            String usuario = auth.getName();
            // return false;
            throw new AccessDeniedException("El usuario:'" + usuario + "' con los roles de: '" + nombreRoles.toString() + "', No tiene permiso para:'" + permission + "'");
        } catch (Exception e) {
            throw e;
        }
        //   return false;
    }
}
