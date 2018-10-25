package cn.xzt.interview.controller;

import cn.xzt.interview.DTO.UserDTO;
import cn.xzt.interview.common.constant.ResultStatus;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.common.utils.StringUtil;
import cn.xzt.interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/22 18:04
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public R login(String username, String password){
        if (StringUtil.isBlank(username) || StringUtil.isBlank(password)){
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        UserDTO userDTO = userService.login(username, password);
        if (null == userDTO){
            return R.error(ResultStatus.ERROR.getCode(), "用户名或者密码错误");
        }else{
            return R.ok(userDTO);
        }
    }
}