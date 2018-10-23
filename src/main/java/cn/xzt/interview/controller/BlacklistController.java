package cn.xzt.interview.controller;

import cn.xzt.interview.DTO.BlacklistDTO;
import cn.xzt.interview.common.constant.ResultStatus;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.common.utils.StringUtil;
import cn.xzt.interview.domain.Blacklist;
import cn.xzt.interview.service.BlacklistService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 黑名单控制类
 * @Auther: 周明军
 * @Date: 2018/10/23 11:02
 */
@RestController
@RequestMapping("/blacklist")
@Slf4j
public class BlacklistController {
    @Autowired
    private BlacklistService blacklistService;

    /**
     * 分页查询黑名单列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public R list(Integer currentPage, Integer pageSize) {
        if (currentPage == null || pageSize == null) {
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        PageUtil<BlacklistDTO> list = blacklistService.list(currentPage, pageSize);
        return R.ok(list);
    }

    /**
     * 加入黑名单
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/create")
    public R create(HttpServletRequest request) {
        String ip = request.getParameter("ip");
        String visitor = request.getParameter("visitor");
        if (StringUtil.isBlank(ip) || StringUtil.isBlank(visitor)) {
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        Blacklist blacklist = new Blacklist();
        blacklist.setIp(ip);
        blacklist.setVisitor(visitor);
        blacklist.setStatus(Blacklist.Status.DISABLE.getCode());
        Blacklist result = blacklistService.create(blacklist);
        return R.ok(result);
    }

    /**
     * 移除黑名单
     *
     * @param id
     * @return
     */
    @GetMapping("remove/{id}")
    public R remove(@PathVariable("id") Integer id) {
        if (id == null) {
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        blacklistService.remove(id);
        return R.ok();
    }
}