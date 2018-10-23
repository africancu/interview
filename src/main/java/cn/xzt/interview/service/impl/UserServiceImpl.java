package cn.xzt.interview.service.impl;

import cn.xzt.interview.DTO.UserDTO;
import cn.xzt.interview.mapper.UserMapper;
import cn.xzt.interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/22 18:05
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO login(String username, String password) {
        return userMapper.login(username,password);
    }


}