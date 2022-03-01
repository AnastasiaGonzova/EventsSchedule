package com.gonzova.EventsSchedule.domain.emuns;

import org.springframework.security.core.GrantedAuthority;

public enum RoleName implements GrantedAuthority {

    ADMIN, HR, USER;

    @Override
    public String getAuthority() {
        return name();
    }

}
