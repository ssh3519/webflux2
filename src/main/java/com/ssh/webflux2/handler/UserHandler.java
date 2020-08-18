package com.ssh.webflux2.handler;

import com.ssh.webflux2.entity.User;
import com.ssh.webflux2.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @description
 * @author: ssh
 * @Date: 2020/8/18 16:37
 */
public class UserHandler {
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据id查询
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        //空值处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<User> userMono = userService.getUserById(id);
        return userMono.flatMap(person -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON).body(fromObject(person))).switchIfEmpty(notFound);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        Flux<User> users = userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);
    }

    /**
     * 保存
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> saveUser(ServerRequest request) {
        //得到对象
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(userService.saveUserInfo(userMono));
    }
}
