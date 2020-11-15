package com.cygao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用于将注册信息以RESTful的方式返回给前端
 * @author cygao
 */
@Data
@AllArgsConstructor
public class ReplyLoginMessage {

  /**
   * 错误信息0：成功
   */
  public static final int SUCCESS = 0;

  /**
   * 错误信息1：用户不存在
   */
  public static final int USERNAME_NOT_EXIST = 1;

  /**
   * 错误信息2：密码错误
   */
  public static final int WRONG_PASSWORD = 2;

  private boolean success;

  private int errorMsg;
}
