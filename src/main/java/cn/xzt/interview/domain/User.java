package cn.xzt.interview.domain;

import lombok.Data;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/22 18:11
 */
@Data
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String name;
    private Integer roleId;
}