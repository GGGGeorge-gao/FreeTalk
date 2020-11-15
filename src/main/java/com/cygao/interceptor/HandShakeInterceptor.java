package com.cygao.interceptor;

import com.cygao.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HandShakeInterceptor extends HttpSessionHandshakeInterceptor {

  /**
   * 总连接人数
   */
  public static final AtomicInteger TOTAL_COUNT = new AtomicInteger(0);

  @Autowired
  private UserServiceImpl userService;

  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
    boolean res = super.beforeHandshake(request, response, wsHandler, attributes);
    if (res) {
      TOTAL_COUNT.incrementAndGet();
      return true;
    } else {
      return false;
    }
  }
}
