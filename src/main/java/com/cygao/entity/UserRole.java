package com.cygao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * 用户角色实体，实现{@link GrantedAuthority}接口
 * @author cygao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements GrantedAuthority {

  private int id;
  private String role;
  private String comment;

  @Override
  public String getAuthority() {
    return role;
  }
}
