package com.cygao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用于将注册信息以RESTful的方式返回给前端
 * @author cygao
 */
@Data
@AllArgsConstructor
public class ReplyRegisterMessage {

  /**
   * 错误信息0：成功
   */
  public static final int SUCCESS = 0;

  /**
   * 错误信息1：用户名已存在
   */
  public static final int USERNAME_EXISTS = 1;

  /**
   * 是否注册成功
   */
  private boolean success;

  /**
   * 错误信息
   */
  private int errorMsg;
}
