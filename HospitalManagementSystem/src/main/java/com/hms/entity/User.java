//package com.hms.entity;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.Email;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@RequiredArgsConstructor
//@Entity
//@Table(name = "users")
//public class User  {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//
//    @Column(name = "username", length = 100, nullable = false, unique = true)
//    private String username;
//
//    @Email
//    @Column(name = "email", length = 100, nullable = false, unique = true)
//    private String email;
//
//    @Column(name = "password", length = 255, nullable = false)
//    private String password;
//
//    @Column(name = "account_locked")
//    private Boolean accountLocked = false;  
//
//    @Column(name = "account_enabled")
//    private Boolean accountEnabled = true; 
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();
//    //Set manually in login logic — 
//    @Column(name = "last_login")
//    private LocalDateTime lastLogin;
//
//    @CreationTimestamp
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//    @Column(name = "created_by", length = 100)
//    private String createdBy;
//
//    @Column(name = "updated_by", length = 100)
//    private String updatedBy;
//    
//}
package com.hms.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hms.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User extends Auditable implements UserDetails {  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    private String username;

    @Email
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "account_locked")
    private Boolean accountLocked = false;

    @Column(name = "account_enabled")
    private Boolean accountEnabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    
   // ── UserDetails overrides ───

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName().name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() 
    { 
    	return true; 
    }
    @Override
    public boolean isAccountNonLocked()
    {
    	return !Boolean.TRUE.equals(accountLocked);
    	}

    @Override
    public boolean isCredentialsNonExpired() 
    { 
    	return true; 
    	}

    @Override
    public boolean isEnabled() 
    {
    	return Boolean.TRUE.equals(accountEnabled); 
    	}
}