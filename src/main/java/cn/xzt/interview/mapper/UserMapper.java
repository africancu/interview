package cn.xzt.interview.mapper;

import cn.xzt.interview.DTO.UserDTO;
import cn.xzt.interview.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/22 18:06
 */
public interface UserMapper {

    UserDTO login(@Param("username") String username, @Param("password") String password);
}