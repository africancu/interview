package cn.xzt.interview.controller;

import cn.xzt.interview.DTO.BlacklistDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.service.BlacklistService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 黑名单控制类
 * @Auther: 周明军
 * @Date: 2018/10/23 11:02
 */
@RestController
@RequestMapping("blacklist")
public class BlacklistController {
    @Autowired
    private BlacklistService blacklistService;

    /**
     * 分页查询黑名单列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("list")
    public R list(Integer currentPage, Integer pageSize){
        if (currentPage == null || pageSize == null){
            return R.error("缺少分页参数");
        }
        PageUtil<BlacklistDTO> list = blacklistService.list(currentPage, pageSize);
        return R.ok(list);
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