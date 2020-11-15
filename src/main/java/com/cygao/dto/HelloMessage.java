package com.cygao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于将Client发送的消息传输到含有@MessageMapping注释的Controller的方法中
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloMessage {

  private String user;

  private String message;
}
