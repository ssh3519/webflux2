package com.ssh.webflux2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description
 * @author: ssh
 * @Date: 2020/8/18 15:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String gender;
    private Integer age;
}
