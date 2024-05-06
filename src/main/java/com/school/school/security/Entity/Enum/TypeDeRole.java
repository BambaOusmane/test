package com.school.school.security.Entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum TypeDeRole {
    ETUDIANT,
    ADMINISTRATEUR,
    PROFESSEUR





//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        final List<SimpleGrantedAuthority> grantedAuthorities = this.permissions.stream().map(
//                permissions -> new SimpleGrantedAuthority(permissions.name())
//        ).collect(Collectors.toList());
//
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return grantedAuthorities;
//    }
}
