package com.gonzova.EventsSchedule.security;

import com.gonzova.EventsSchedule.domain.entity.Credential;
import com.gonzova.EventsSchedule.domain.entity.Employee;
import com.gonzova.EventsSchedule.domain.entity.Role;
import com.gonzova.EventsSchedule.repository.CredentialRepository;
import com.gonzova.EventsSchedule.service.TokenService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtRequestFilter extends OncePerRequestFilter {

    @NonNull
    private TokenService tokenService;

    @NonNull
    private CredentialRepository credentialRepository;

    @Override
    @SneakyThrows
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String token = request.getHeader("AUTHORIZATION");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (token != null && securityContext.getAuthentication() == null) {
            String login = tokenService.extractUsernameAndValidate(token);
            Credential credential = credentialRepository.findByLogin(login)
                    .orElseThrow();
            Employee employee = credentialRepository.findEmployeeByCredentialId(credential.getId())
                    .orElseThrow();

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            Set<Role> roles = employee.getRoles();
            for(Role r :  roles){
                authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            employee,
                            null,
                           authorities);
            securityContext.setAuthentication(usernamePasswordAuthenticationToken);
        }
            filterChain.doFilter(request, response);

    }
}