package com.cygao.controller;

import com.cygao.dao.RolesRepository;
import com.cygao.entity.User;
import com.cygao.entity.UserRole;
import com.cygao.service.UserService;
import com.cygao.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cygao
 */
@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {

  private final UserServiceImpl userService;
  private final RolesRepository rolesRepo;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public RegisterController(UserServiceImpl userService, RolesRepository rolesRepo, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.rolesRepo = rolesRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping
  public String registerGet() {
    return "register";
  }

  @ModelAttribute("user")
  public User user() {
    return new User();
  }

  @PostMapping
  public String registerPost(@ModelAttribute("user") User user) {
    log.info("user get: " + user.toString());

    if (userService.isExsitByUsername(user.getName())) {
      return "";
    } else {
      userService.addUser(user.getName(), passwordEncoder.encode(user.getPassword()));
      log.info("User 存储成功");
    }

    user = userService.findUserByUsername(user.getName());
    user.getRoles().add(rolesRepo.findRoleByName("ROLE_ORDINARY"));
    rolesRepo.addUser(user);

    return "redirect:/";
  }
}
