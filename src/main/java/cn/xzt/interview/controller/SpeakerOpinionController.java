package cn.xzt.interview.controller;

import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.common.utils.StringUtil;
import cn.xzt.interview.domain.Interview;
import cn.xzt.interview.domain.SpeakerOpinion;
import cn.xzt.interview.service.SpeakerOpinionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/11/1 17:48
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

    }
