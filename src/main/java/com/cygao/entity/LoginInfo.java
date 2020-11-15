package com.cygao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 登入登出实体
 * @author cygao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
  private int userId;

  private String username;

  private int status;

  private Date handleTime;
}
