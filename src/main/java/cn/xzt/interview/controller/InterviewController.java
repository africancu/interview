package cn.xzt.interview.controller;

import cn.xzt.interview.DTO.InterviewDTO;
import cn.xzt.interview.DTO.SpeakerDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.service.InterviewService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/10/23 11:54
 */
@RestController
@RequestMapping(value = "interview")
@Slf4j
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @RequestMapping("/list")
    public R findList(String status, Integer currentPage, Integer pageSize) {
        R basicResponse = new R();
        try {
            if (currentPage == null || pageSize == null){
                basicResponse.setCode(500);
                basicResponse.setMessage("缺少分页参数");
                return basicResponse;
            }
            PageHelper.startPage(currentPage, pageSize);
            PageUtil<InterviewDTO> idto=interviewService.findAll(status,currentPage,pageSize);

            basicResponse.setCode(200);
            basicResponse.setMessage("成功");
            basicResponse.setResult(idto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicResponse;
    }

    @RequestMapping("/query")
    public R query(String id) {
        R basicResponse = new R();

        try {
            InterviewDTO idto=interviewService.selectByPrimaryKey(id);
            List<SpeakerDTO> speakerDTOList=interviewService.findByinterviewId(id);
            if(speakerDTOList!=null && speakerDTOList.size()>0){
                idto.setSpeakerList(speakerDTOList);
            }
            basicResponse.setCode(200);
            basicResponse.setMessage("成功");
            basicResponse.setResult(idto);
        } catch (Exception e) {
            basicResponse.setCode(500);
            basicResponse.setMessage("程序错误");
            e.printStackTrace();
        }
        return basicResponse;

    }

}
