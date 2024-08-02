package ir.snapp.pay.side.project.vamak.core.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;

public class MemberDetails implements UserDetails {

    @Serial
    private static final long serialVersionUID = -5847938417291040867L;

    private final String username;
    private final String password;
    private final boolean inactive;

    public MemberDetails(String username, String password, boolean inactive) {
        this.username = username;
        this.password = password;
        this.inactive = inactive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !inactive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !inactive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !inactive;
    }

    @Override
    public boolean isEnabled() {
        return !inactive;
    }
}
