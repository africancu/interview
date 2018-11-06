package cn.xzt.interview.controller;

import cn.xzt.interview.DTO.SpeakerOpinionDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.common.utils.StringUtil;
import cn.xzt.interview.domain.SpeakerOpinion;
import cn.xzt.interview.service.SpeakerOpinionService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public R sava(@Valid SpeakerOpinion speakerOpinion, BindingResult bindingResult) throws Exception {
        R basicResponse = new R();

        try {
            if(StringUtil.isBlank(speakerOpinion.getContent())){
                basicResponse.setCode(300);
                basicResponse.setMessage("实录文字不能为空！");
            }
            if(null!=speakerOpinion.getSpeakerId() && speakerOpinion.getSpeakerId()<=0){
                basicResponse.setCode(300);
                basicResponse.setMessage("嘉宾不能为空！");
            }
            speakerOpinion.setEnable(0);
            speakerOpinionService.addspeakerOpinion(speakerOpinion);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicResponse;

    }


    @RequestMapping("/edit")
    public R edit(@Valid SpeakerOpinion speakerOpinion, BindingResult bindingResult) throws Exception {
        R basicResponse = new R();

        try {
            if(null==speakerOpinion.getOpinionId()){
                basicResponse.setCode(300);
                basicResponse.setMessage("编号不能为空！");
            }
            speakerOpinionService.updateByPrimaryKeySelective(speakerOpinion);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicResponse;

    }

    @RequestMapping("/remove")
    public R remove(String[] ids) throws Exception {
        R basicResponse = new R();

        try {
            if(ids==null || ids.length==0){
                basicResponse.setCode(300);
                basicResponse.setMessage("编号不能为空！");
            }
            speakerOpinionService.romove(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicResponse;

    }

    @RequestMapping("/list")
    public R findAll(String interviewId,Integer currentPage, Integer pageSize) throws Exception {
        R basicResponse = new R();

        try {
            if(StringUtil.isBlank(interviewId)){
                basicResponse.setCode(300);
                basicResponse.setMessage("实录文字不能为空！");
            }
            if (currentPage == null || pageSize == null){
                basicResponse.setCode(600);
                basicResponse.setMessage("缺少分页参数");
                return basicResponse;
            }
            PageHelper.startPage(currentPage, pageSize);
            PageUtil<SpeakerOpinionDTO> idto=speakerOpinionService.findAll(interviewId,currentPage,pageSize);
            basicResponse.setCode(200);
            basicResponse.setMessage("成功");
            basicResponse.setResult(idto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicResponse;

    }

    }
