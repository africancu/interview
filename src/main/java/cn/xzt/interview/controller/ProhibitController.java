package cn.xzt.interview.controller;

import cn.xzt.interview.common.utils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 11:09
 */
@RestController
@RequestMapping("prohibit")
public class ProhibitController {

    @GetMapping("list")
    public R list(){
        return null;
    }

    @PostMapping("create")
    public R create(){
        return null;
    }

    @GetMapping("remove/{id}")
    public R remove(@PathVariable Integer id){
        return null;
    }

}