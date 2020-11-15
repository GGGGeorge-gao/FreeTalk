package com.cygao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体，实现{@link UserDetails}接口，用于用户鉴权
 * @author cygao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

  private int id;
  private String name;
  private String password;

  private List<UserRole> roles = new ArrayList<>();


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>(roles);
  }

  @Override
  public String getUsername() {
    return name;
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
}
