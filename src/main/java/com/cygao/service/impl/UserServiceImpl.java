package com.cygao.service.impl;

import com.cygao.dao.RolesRepository;
import com.cygao.dao.UserRepository;
import com.cygao.entity.User;
import com.cygao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author cygao
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepo;
  private final RolesRepository rolesRepo;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepo, RolesRepository rolesRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.rolesRepo = rolesRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findUserByName(username);

    if (user == null) {
      throw new UsernameNotFoundException("用户名不存在");
    }
    user.setRoles(rolesRepo.findUserRoles(user));

    log.info(user.toString());
    return user;
  }

  @Override
  public void addUser(String name, String password) {
    userRepo.addUser(name, password);
  }

  @Override
  public boolean isExsitByUsername(String name) {
    return userRepo.findUserByName(name) != null;
  }

  @Override
  public boolean validatePassword(String name, String password) {
    return passwordEncoder.matches(password, userRepo.findUserByName(name).getPassword());
  }

  @Override
  public User findUserByUsername(String name) {
    return userRepo.findUserByName(name);
  }
}
