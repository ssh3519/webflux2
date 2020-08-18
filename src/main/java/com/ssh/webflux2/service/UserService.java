package com.ssh.webflux2.service;

import com.ssh.webflux2.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @description Mono(一个或0个元素)  Flux（多个元素）
 * @author: ssh
 * @Date: 2020/8/18 15:42
 */
public interface UserService {
    /**
     * 根据id查询
     */
    Mono<User> getUserById(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    Flux<User> getAllUser();

    /**
     * 添加用户
     * @param user
     * @return
     */
    Mono<Void> saveUserInfo(Mono<User> user);
}
