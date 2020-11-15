package com.cygao.dao;

import com.cygao.entity.Message;
import com.cygao.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author cygao
 */
public interface MessageRepository {

  void addMessage(@Param("message") Message message);

  Message findMessageById(@Param("id") int id);

  List<Message> findMessageByDataRange(@Param("from") Date from, @Param("to") Date to);

  List<Message> findMessageByUser(@Param("user") User user);
}
