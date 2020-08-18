package com.ssh.webflux2;

import com.ssh.webflux2.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @description
 * @author: ssh
 * @Date: 2020/8/18 18:07
 */
public class Client {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://127.0.0.1:65000");

        //根据id查询
        //String id = "1";
        //User user = webClient.get().uri("/user/{id}", 1)
        //        .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(User.class).block();
        //System.out.println(user);

        //查询所有
        Flux<User> userFlux = webClient.get().uri("/user")
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(User.class);
        userFlux.map(user -> user).buffer().doOnNext(System.out::println).blockFirst();
    }
}
