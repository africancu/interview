package cn.xzt.interview.service;

import cn.xzt.interview.DTO.UserDTO;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/22 18:05
 */
public interface UserService {

    UserDTO login(String username, String password);

}