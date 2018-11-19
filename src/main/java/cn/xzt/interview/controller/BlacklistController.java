package cn.xzt.interview.controller;

import cn.xzt.interview.DTO.BlacklistDTO;
import cn.xzt.interview.common.constant.ResultStatus;
import cn.xzt.interview.common.utils.*;
import cn.xzt.interview.domain.Blacklist;
import cn.xzt.interview.service.BlacklistService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public R list(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        PageUtil<BlacklistDTO> list = blacklistService.list(pageNum, pageSize);

        RList ok = new RList();
        ok.setData(list.getList());
        ok.setPageNum(list.getPageNum());
        ok.setPageSize(list.getPageSize());
        ok.setPages(list.getPages());
        ok.setSize(list.getSize());
        ok.setCount(list.getTotal());

        return ok;
    }

//    /**
//     * 加入黑名单
//     *
//     * @param request
//     * @return
//     */
//    @PostMapping(value = "/create")
//    public R create(HttpServletRequest request) {
//        String ip = request.getParameter("ip");
//        String visitor = request.getParameter("visitor");
//        if (StringUtil.isBlank(ip) || StringUtil.isBlank(visitor)) {
//            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMsg());
//        }
//        Blacklist blacklist = new Blacklist();
//        blacklist.setIp(ip);
//        blacklist.setVisitor(visitor);
//        blacklist.setStatus(Blacklist.Status.DISABLE.getCode());
//        Blacklist data = blacklistService.create(blacklist);
//        return R.ok(data);
//    }

    /**
     * 加入黑名单
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/create")
    public R create(@RequestParam String params) {

        R response = ParamCheckUtil.checkPrams(params, "ip","visitor");
        if (response != null) {
            return response;
        }

        JSONObject jsonObject = JSONObject.parseObject(params);

        String ip = jsonObject.getString("ip");
        String visitor = jsonObject.getString("visitor");
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