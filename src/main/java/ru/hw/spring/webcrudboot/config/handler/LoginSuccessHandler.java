package ru.hw.spring.webcrudboot.config.handler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.hw.spring.webcrudboot.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserDetailsService userDetailsService;

    public LoginSuccessHandler(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String name = httpServletRequest.getParameter("email");
        long id = ((User) userDetailsService.loadUserByUsername(name)).getId();
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/");
        } else {
            httpServletResponse.sendRedirect("/user/" + id);
        }
    }
}