package com.futvia.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // Obtener todos los roles del usuario
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/dashboard";

        for (GrantedAuthority authority : authorities) {
            String rol = authority.getAuthority();

            switch (rol) {
                case "ROLE_MASTER":
                case "ROLE_SUPER_ADMIN":
                    redirectUrl = "/admin/panel";
                    break;
                case "ROLE_ADMIN":
                    redirectUrl = "/admin/dashboard";
                    break;
                case "ROLE_OPERADOR":
                case "ROLE_LIGA_MANAGER":
                    redirectUrl = "/operador/liga";
                    break;
                case "ROLE_ENTRENADOR":
                case "ROLE_CAPITAN_EQUIPO":
                    redirectUrl = "/equipo/panel";
                    break;
                case "ROLE_JUGADOR":
                    redirectUrl = "/jugador/perfil";
                    break;
                case "ROLE_MODERADOR":
                    redirectUrl = "/comunidad/moderacion";
                    break;
                case "ROLE_SOPORTE":
                    redirectUrl = "/soporte/dashboard";
                    break;
                default:
                    redirectUrl = "/dashboard"; // Fallback
            }

            // Solo el primer rol válido define el destino
            break;
        }

        log.info("Usuario redirigido a: {}", redirectUrl);
        response.sendRedirect(redirectUrl);
    }
}
