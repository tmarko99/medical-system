package it.engineering.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthResponse {
    private String email;
    private String accessToken;
    private String tokenType = "Bearer";
    private Collection<? extends GrantedAuthority> authorities;

    public JwtAuthResponse(String accessToken, String email, Collection<? extends GrantedAuthority> authorities) {
        this.accessToken = accessToken;
        this.email = email;
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
