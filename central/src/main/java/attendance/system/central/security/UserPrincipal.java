package attendance.system.central.security;

import attendance.system.central.models.entities.AuthorizationEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public class UserPrincipal implements UserDetails {

    private AuthorizationEntity authorizationEntity;

    private GrantedAuthority authority;

    public UserPrincipal(AuthorizationEntity authorizationEntity, GrantedAuthority authority) {
        this.authorizationEntity = authorizationEntity;
        this.authority = authority;
    }

    public static UserPrincipal create(AuthorizationEntity authorizationEntity) {
        GrantedAuthority authority = new SimpleGrantedAuthority(authorizationEntity.getEntityType().toString());

        return new UserPrincipal(
                new AuthorizationEntity(authorizationEntity.getId(), authorizationEntity.getPassword(), authorizationEntity.getEntityType()),
                authority
        );
    }

    public AuthorizationEntity getAuthorizationEntity() {
        return authorizationEntity;
    }

    public void setAuthorizationEntity(AuthorizationEntity authorizationEntity) {
        this.authorizationEntity = authorizationEntity;
    }

    public GrantedAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(GrantedAuthority authority) {
        this.authority = authority;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(authorizationEntity.getEntityType().toString()));
        return authority;
    }

    @Override
    public String getPassword() {
        return authorizationEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return authorizationEntity.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPrincipal)) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(authorizationEntity, that.authorizationEntity) &&
                Objects.equals(authority, that.authority);
    }
}
