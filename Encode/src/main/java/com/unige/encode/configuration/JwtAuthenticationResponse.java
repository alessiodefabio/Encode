package com.unige.encode.configuration;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
