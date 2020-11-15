package com.cygao.entity;

import com.cygao.enums.MessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 消息实体
 * @author cygao
 */
@Data
@Slf4j
@AllArgsConstructor
public class Message {

  private int userId;

  private String name;

  private String content;

  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private Date createdAt;

  private MessageTypeEnum messageType;

  public Message(Integer userId, String name, String content, Timestamp createdAt, Integer messageType) {
    this.userId = userId;
    this.name = name;
    this.content = content;
    this.createdAt = new Date(createdAt.getTime());
    this.messageType = MessageTypeEnum.getMessageType(messageType);
  }
}
