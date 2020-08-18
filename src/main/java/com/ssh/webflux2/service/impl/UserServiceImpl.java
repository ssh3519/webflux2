package com.ssh.webflux2.service.impl;

import com.ssh.webflux2.entity.User;
import com.ssh.webflux2.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author: ssh
 * @Date: 2020/8/18 15:46
 */
@Service
public class UserServiceImpl implements UserService {
    //模拟数据
    private final Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl() {
        this.users.put(1, new User("aaa", "男", 10));
        this.users.put(2, new User("bbb", "女", 11));
        this.users.put(3, new User("ccc", "男", 12));
        this.users.put(4, new User("ddd", "女", 13));
    }

    @Override
    public Mono<User> getUserById(Integer id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        return userMono.doOnNext(person -> {
            int id = this.users.size() + 1;
            users.put(id, person);
        }).thenEmpty(Mono.empty());
    }
}
