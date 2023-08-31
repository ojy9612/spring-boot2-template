package com.zeki.springboot2template.security;



import com.zeki.springboot2template.exception.APIException;
import com.zeki.springboot2template.exception.ResponseCode;
import com.zeki.springboot2template.security.em.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final String userId;

    @Getter
    private final String name;
    private final String password;

    private final List<UserRole> userRoleList;

    public UserDetailsImpl(String userEntity) {
        this.userId = null;
        this.name = null;
        this.password = null;
        this.userRoleList = null;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.userRoleList.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.name()))
                .toList();
    }

    public void isForbid(UserRole userRole) {
        this.userRoleList.stream()
                .filter(role -> role.equals(userRole))
                .findFirst()
                .orElseThrow(() -> new APIException(ResponseCode.UNMODIFIABLE_INFORMATION, "해당 권한이 없습니다. 권한명 : " + userRole.getDescription()));
    }
}