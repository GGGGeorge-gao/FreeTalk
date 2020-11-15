package com.cygao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用于前端的用户填写的注册或登录的信息以JSON的方式传到后端
 * @author cygao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateMessage {

  private String user;

  private String password = "";

  public ValidateMessage(String user) {
    this.user = user;
  }
}
