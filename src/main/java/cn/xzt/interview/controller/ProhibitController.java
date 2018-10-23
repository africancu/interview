package cn.xzt.interview.controller;

import cn.xzt.interview.common.constant.ResultStatus;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.common.utils.StringUtil;
import cn.xzt.interview.domain.ProhibitWord;
import cn.xzt.interview.service.ProhibitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 11:09
 */
@RestController
@RequestMapping("/prohibit")
@Slf4j
public class ProhibitController {
    @Autowired
    private ProhibitService prohibitService;

    @PostMapping(value = "/list", produces = "application/json;charset=utf-8")
    public R list(HttpServletRequest request){
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String keyWord = request.getParameter("keyWord");
        if (StringUtil.isBlank(pageNum) || StringUtil.isBlank(pageSize)){
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        PageUtil<ProhibitWord> list = prohibitService.list(Integer.parseInt(pageNum), Integer.parseInt(pageSize), keyWord);
        return R.ok(list);
    }

    /**
     * 添加关键字
     * @param request
     * @return
     */
    @PostMapping("/create")
    public R create(HttpServletRequest request){
        String keyWord = request.getParameter("keyWord");
        if (StringUtil.isBlank(keyWord)){
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        ProhibitWord prohibitWord = new ProhibitWord();
        prohibitWord.setWord(keyWord);
        try {
            prohibitService.create(prohibitWord);
        }catch (Exception e){
            return R.error(ResultStatus.BAD_REQUEST.getCode(), "该关键字已存在");
        }
        return R.ok(prohibitWord);
    }

    /**
     * 批量删除关键字
     * @param request
     * @return
     */
    @PostMapping("/remove")
    public R remove(HttpServletRequest request){
        String ids = request.getParameter("ids");
        log.info("传入的ids为----------> {}", ids);
        if (StringUtil.isBlank(ids)){
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        String[] split = ids.split(",");
        List<String> list = Arrays.asList(split);
        prohibitService.remove(list);
        return R.ok();
    }

}