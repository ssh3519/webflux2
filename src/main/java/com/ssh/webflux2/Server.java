package com.ssh.webflux2;

import com.ssh.webflux2.handler.UserHandler;
import com.ssh.webflux2.service.UserService;
import com.ssh.webflux2.service.impl.UserServiceImpl;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * @description
 * @author: ssh
 * @Date: 2020/8/18 17:30
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }

    //创建Router路由
    public RouterFunction<ServerResponse> routerFunction() {
        UserService userService = new UserServiceImpl();
        UserHandler handler = new UserHandler(userService);
        //设置路由
        return RouterFunctions.route(GET("/user/{id}").and(accept(APPLICATION_JSON)), handler::getUserById)
                .andRoute(GET("/user").and(accept(APPLICATION_JSON)), handler::getAllUsers);
    }

    //创建服务器完成适配
    public void createReactorServer() {
        //路由和handler适配
        RouterFunction<ServerResponse> routerFunction = routerFunction();
        HttpHandler httpHandler = toHttpHandler(routerFunction);
        ReactorHttpHandlerAdapter handlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);

        //创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(handlerAdapter).bindNow();
    }
}
