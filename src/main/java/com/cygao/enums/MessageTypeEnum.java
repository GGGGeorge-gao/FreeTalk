package com.cygao.enums;

/**
 * 消息类型枚举类型，表明用户发送的消息类型(在未来的版本中可能会支持用户发送图片或文件等类型的消息
 * @author cygao
 */
public enum MessageTypeEnum {
  /**
   *
   */
  TEXT("1"), IMAGE("2");

  public final String type;

  MessageTypeEnum(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public static MessageTypeEnum getMessageType(int val) {
    return MessageTypeEnum.values()[val];
  }
}
