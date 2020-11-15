package com.cygao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于用户连接或断开时从Client端传回Server的消息
 * @author cygao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectMessage {

  public static final int CONNECT = 1;
  public static final int DISCONNECT = 0;

  private String user;

  private int isAdd;
}
