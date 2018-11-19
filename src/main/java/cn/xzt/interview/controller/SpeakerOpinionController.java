package cn.xzt.interview.controller;

import cn.xzt.interview.DTO.SpeakerOpinionDTO;
import cn.xzt.interview.common.constant.ResultStatus;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.ParamCheckUtil;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.common.utils.StringUtil;
import cn.xzt.interview.domain.SpeakerOpinion;
import cn.xzt.interview.service.SpeakerOpinionService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/11/1 17:48
 * 文字实录
 */

@RestController
@RequestMapping(value = "speakerOpinion")
@Slf4j
public class SpeakerOpinionController {
    @Autowired
    private SpeakerOpinionService speakerOpinionService;


    @RequestMapping("/create")
    public R sava(@RequestBody SpeakerOpinion speakerOpinion, BindingResult bindingResult) throws Exception {
        R basicResponse = new R();

        try {
            if (StringUtil.isBlank(speakerOpinion.getContent())) {
                basicResponse.setCode(300);
                basicResponse.setMsg("实录文字不能为空！");
            }
            if (null != speakerOpinion.getSpeakerId() && speakerOpinion.getSpeakerId() <= 0) {
                basicResponse.setCode(300);
                basicResponse.setMsg("嘉宾不能为空！");
            }
            speakerOpinion.setEnable(0);
            speakerOpinionService.addSpeakerOpinion(speakerOpinion);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicResponse;

    }


    @PostMapping("/edit")
    public R edit(@RequestBody SpeakerOpinion speakerOpinion, BindingResult bindingResult) throws Exception {
        R basicResponse = new R();

        try {
            if (null == speakerOpinion.getOpinionId()) {
                basicResponse.setCode(300);
                basicResponse.setMsg("编号不能为空！");
            }
            speakerOpinionService.updateByPrimaryKeySelective(speakerOpinion);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicResponse;

    }

    @RequestMapping("/remove")
    public R remove(@RequestBody String params) {
        R response = ParamCheckUtil.checkPrams(params, "ids");
        if (response != null) {
            return response;
        }

        JSONObject jsonObject = JSONObject.parseObject(params);

        final JSONArray idsArray = jsonObject.getJSONArray("ids");

        if (idsArray.isEmpty()) {
            return R.error(300, "编号不能为空");
        }
        try {
            Integer[] ids = idsArray.toArray(new Integer[idsArray.size()]);
            speakerOpinionService.remove(ids);
        } catch (ArrayStoreException e) {
            return R.error(ResultStatus.PARAM_ERROR.getCode(), ResultStatus.PARAM_ERROR.getMessage()+" ：id 为 int 类型");
        }

        return R.ok();
    }

    @RequestMapping("/list")
    public R findAll(String interviewId, Integer currentPage, Integer pageSize) throws Exception {
        R basicResponse = new R();

        try {
            if (StringUtil.isBlank(interviewId)) {
                basicResponse.setCode(300);
                basicResponse.setMsg("实录文字不能为空！");
            }
            if (currentPage == null || pageSize == null) {
                basicResponse.setCode(600);
                basicResponse.setMsg("缺少分页参数");
                return basicResponse;
            }
            PageHelper.startPage(currentPage, pageSize);
            PageUtil<SpeakerOpinionDTO> idto = speakerOpinionService.findAll(interviewId, currentPage, pageSize);
            basicResponse.setCode(200);
            basicResponse.setMsg("成功");
            basicResponse.setData(idto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicResponse;

    }

}
