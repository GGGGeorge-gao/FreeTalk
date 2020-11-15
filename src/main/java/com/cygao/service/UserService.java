package com.cygao.service;

import com.cygao.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author cygao
 */
public interface UserService extends UserDetailsService {

  void addUser(String name, String password);

  boolean isExsitByUsername(String name);

  boolean validatePassword(String name, String password);

  User findUserByUsername(String name);

}
