package com.cygao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 若有用户连接或断开，则会将当前在线人数以广播的形式传输到所有Client端
 * @author cygao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountChangeMessage {

  private int count;
}
