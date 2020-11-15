package com.cygao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于后端将
 * @author cygao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

  private String user;

  private String content;

  private String date;
}
