package com.cygao.controller;

import com.cygao.dto.ReplyLoginMessage;
import com.cygao.dto.ReplyRegisterMessage;
import com.cygao.dto.ValidateMessage;
import com.cygao.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author cygao
 */
@Slf4j
@RestController
public class ValidateController {

  private final UserServiceImpl userService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public ValidateController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * 验证用户注册的用户名是否有效
   * @param msg {@link com.cygao.dto.ValidateMessage} 验证信息
   * @return {@link com.cygao.dto.ReplyRegisterMessage} 返回验证信息
   */
  @RequestMapping(value = "api/register", method = RequestMethod.POST)
  public ReplyRegisterMessage replyRegister(@RequestBody ValidateMessage msg) {
    log.info(msg.toString());
    boolean isExist = userService.isExsitByUsername(msg.getUser());
    if (!isExist) {
      log.info("{} register successfully", msg.getUser());
      return new ReplyRegisterMessage(true, ReplyRegisterMessage.SUCCESS);
    } else {
      log.info("{} register failed", msg.getUser());
      return new ReplyRegisterMessage(false, ReplyRegisterMessage.USERNAME_EXISTS);
    }
  }

  /**
   * 验证用户登录的账号密码是否有误，若有误则返回会错误信息
   * @param msg {@link com.cygao.dto.ValidateMessage} 验证信息
   * @return {@link com.cygao.dto.ReplyLoginMessage} 返回验证信息
   */
  @RequestMapping(value = "api/login", method = RequestMethod.POST)
  public ReplyLoginMessage replyLogin(@RequestBody ValidateMessage msg) {
    log.info(msg.toString());
    if (!userService.isExsitByUsername(msg.getUser())) {
      // 该用户不存在
      log.info("username[{}] does not exist", msg.getUser());
      return new ReplyLoginMessage(false, ReplyLoginMessage.USERNAME_NOT_EXIST);
    } else if (!userService.validatePassword(msg.getUser(), msg.getPassword())) {
      // 密码不正确
      log.info("{} login failed because of wrong password", msg.getUser());
      return new ReplyLoginMessage(false, ReplyLoginMessage.WRONG_PASSWORD);
    } else {
      // 验证成功
      log.info("{} login successfully", msg.getUser());
      return new ReplyLoginMessage(true, ReplyLoginMessage.SUCCESS);
    }
  }
}
