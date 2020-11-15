## 一、关于项目

> 项目在线体验地址：http://talk.cygao.top:8848/

**使用该源码希望能注明原博客以及源码出处，如果觉得还可以的话也可以动动手指点一个star，谢谢！**

一个简易的在线聊天室项目（SpringBoot + MyBatis + WebSocket）



## 二、技术栈

**后端：**

- 核心框架：SpringBoot  v2.3.4
- 用户鉴权：Spring Security
- 持久层框架：MyBatis   v3.4.2
- 数据库：MySQL  v8.0.21
- JSON解析库：FastJson  v1.2.70
- 测试框架：JUnit
- 加密：BCrypt加密



**前端**：

- CSS/HTML框架：[Bootstrap3](https://v3.bootcss.com/)

- JS框架：jQuery

- 传输协议：[Stomp](http://stomp.github.io/)

- 模板引擎：[Thymeleaf](https://www.thymeleaf.org/documentation.html)

- > SockJS的一大好处在于提供了浏览器兼容性。优先使用原生WebSocket，如果在不支持websocket的浏览器中，会自动降为轮询的方式。 

- SockJS



## 三、项目运行与部署

1. 在MySQL中创建一个名为 FreeTalk 的数据库

   ```mysql
   mysql> create database FreeTalk;
   ```

   

2. 将该项目克隆到本地并在 Intellij IDEA 中打开该项目，等待 Maven 完成依赖包的加载

   

3. 在 application.yml 中配置数据源属性

   ```yaml
   spring:
     datasource:
   
       username: {username}   // 修改此处
       password: {Password}   // 修改此处
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://{YourMySQLHost}:3306/FreeTalk?serverTimezone=UTC&characterEncoding=utf8  //修改此处
   
       schema: classpath:sql/schema.sql
       data: classpath:sql/data.sql
       sql-script-encoding: utf-8
       
       // initialization-mode在第一次运行时为always
       // always代表每次都会执行/sql中的schema.sql与data.sql完成数据库的初始化
       // 在建表及插入初始数据完成需可改为never
       initialization-mode: always
   ```

   

4. 运行 FreeTalkApplication



## 四、WebSocket 原理与集成消息代理

传统 HTTP 请求响应客户端服务器交互图

![](https://gitee.com/gaochengyu/blogimage/raw/master/img/20201115144615.png)

WebSocket 请求响应客户端服务器交互图

![](https://gitee.com/gaochengyu/blogimage/raw/master/img/20201115144701.png)

- WebSocket 是一种双向通信协议，WebSocket 服务器和 Browser/Client Agent 都能主动的向对方发送或接收数据；
- WebSocket 需要首先用 HTTP 进行握手，在握手的 HTTP 请求头中会有一栏 "Upgrade：websocket”，代表在握手成功后将转换为 WebSocket 协议



**在SpringBoot中集成WebSocket消息代理**

消息代理的简单流程图

![](https://gitee.com/gaochengyu/blogimage/raw/master/img/20201115145212.png)

- `clientInboundChannel` — 用于传输从webSocket客户端接收的消息

- `clientOutboundChannel` — 用于传输向webSocket客户端发送的消息

- `brokerChannel` — 用于传输从服务器端应用程序代码向消息代理发送消息

一次完整的消息接收发送步骤如下：

1. 首先须在实现了`WebSocketMessageBrokerConfigurer`的配置中注册 Endpoint，使客户端 能和Server端建立连接

   ```java
   registry.addEndpoint("/websocket")
   ```

2. Client 端执行JS如下代码，完成与Server端的连接

   ```javascript
   var socket = new SockJS('/websocket');   
   stompClient = Stomp.over(socket);
   ```

3. 注册简单消息代理与Server端接受消息的url前缀

   ```java
   registry.enableSimpleBroker("/topic");
   registry.setApplicationDestinationPrefixes("/app");
   ```

4. Client 端发送消息

   ```javascript
   if (stompClient != null && $("#message").val() !== "") {
       // 以JSON格式发送消息到/app/hello
       // 注意/app恰好是我们在registry.setApplicationDestinationPrefixes中注册过的
       stompClient.send("/app/hello", {}, JSON.stringify({
           'user': username,
           'message': $("#message").val()
       }));
       // 将输入栏置空
       $("#message").val("");
   }
   ```

5. 在Controller中接收

   ```java
   // MessageMapping表示回接收/app/hello发送过来的所有消息
   // SendTo表示将以boardcast广播的方式将消息发送给所有客户端到/topic/greetings
   @MessageMapping("/hello")
   @SendTo("/topic/greetings")
   public ResponseMessage greeting(HelloMessage msg) {
       // 处理过程
       return new ResponseMessage(...);
   }
   ```

6. 在客户端接收

   ```javascript
   stompClient.connect({}, function (frame) {
   	// 在与Server端连接成功后，客户端将订阅/topic/greetings发送过来的所有消息并将其展示
       stompClient.subscribe('/topic/greetings', function (greeting) {
           showMessage(JSON.parse(greeting.body));
       });
   }
   ```

   

## 五、相关文档

1. Spring Security官方文档：https://docs.spring.io/spring-security/site/docs/current/reference/html5/#session-mgmt
2. Spring-framework官方文档WebSocket部分（极力推荐！可以仔细研究）：https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket

3. 使用WebSocket构建一个简易的交互式web应用程序官方教程（推荐）：https://spring.io/guides/gs/messaging-stomp-websocket/

## 六、联系
Email: cygao0828@foxmail.com
