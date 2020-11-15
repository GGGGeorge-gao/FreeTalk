package com.cygao.controller;

import com.cygao.dao.LoginInfoRepository;
import com.cygao.dao.MessageRepository;
import com.cygao.dto.ConnectMessage;
import com.cygao.dto.CountChangeMessage;
import com.cygao.dto.HelloMessage;
import com.cygao.dto.ResponseMessage;
import com.cygao.entity.LoginInfo;
import com.cygao.entity.Message;
import com.cygao.entity.User;
import com.cygao.enums.MessageTypeEnum;
import com.cygao.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cygao
 */
@Slf4j
@Controller
public class WebSocketController {

  private final UserServiceImpl userService;
  private final MessageRepository messageRepo;
  private final LoginInfoRepository loginInfoRepo;

  private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);
  private static final SimpleDateFormat DATE_FORMATE = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  @Autowired
  public WebSocketController(UserServiceImpl userService, MessageRepository messageRepo, LoginInfoRepository loginInfoRepo) {
    this.userService = userService;
    this.messageRepo = messageRepo;
    this.loginInfoRepo = loginInfoRepo;
  }

  /**
   * 接受客户端发送的消息，存储到数据库中，并以广播的形式传输给所有Client
   */
  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public ResponseMessage greeting(HelloMessage msg) {
    log.info(msg.toString());
    ResponseMessage res = new ResponseMessage(msg.getUser(), HtmlUtils.htmlEscape(msg.getMessage()), DATE_FORMATE.format(new Date()));
    User user = userService.findUserByUsername(msg.getUser());
    Message message = new Message(user.getId(), user.getName(), msg.getMessage(), new Date(), MessageTypeEnum.TEXT);
    messageRepo.addMessage(message);
    log.info(res.toString());

    return res;
  }

  /**
   * 记录当前连接人数，将用户登入登出消息存储到数据库中，并将在线人数以广播的形式传输给所有Client
   */
  @MessageMapping("/count")
  @SendTo("/topic/count")
  public CountChangeMessage countChange(ConnectMessage msg) {
    User user = userService.findUserByUsername(msg.getUser());
    LoginInfo info = new LoginInfo(user.getId(), user.getName(), msg.getIsAdd(), new Date());
    loginInfoRepo.addLoginInfo(info);

    log.info(msg.toString());
    if (msg.getIsAdd() == ConnectMessage.CONNECT) {
      ONLINE_COUNT.incrementAndGet();
    } else if (msg.getIsAdd() == ConnectMessage.DISCONNECT) {
      ONLINE_COUNT.decrementAndGet();
    }
    return new CountChangeMessage(ONLINE_COUNT.get());
  }
}
