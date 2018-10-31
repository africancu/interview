package cn.xzt.interview.controller;

import cn.xzt.interview.DTO.InterviewDTO;
import cn.xzt.interview.DTO.SpeakerDTO;
import cn.xzt.interview.common.utils.FileUploadUtil;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.domain.Interview;
import cn.xzt.interview.service.InterviewService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    /**
     * 新增访谈
     *
     * @param files
     * @param interview
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/create")
    public R sava(MultipartFile files, @Valid Interview interview, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        R basicResponse = new R();

        try {
            if (files != null && files.getSize() != 0
                    && !"0".equals(files.getSize())) {
                String img = FileUploadUtil.upload(files, request);
                if (interview.getType() == "0" || "0".equals(interview.getType())) { //视频
                    interview.setPreVideoUrl(img);
                } else {
                    //图文预告
                    interview.setPrePicUrl(img);
                }
            }
            interviewService.insertInterview(interview);
            basicResponse.setCode(200);
            basicResponse.setMessage("成功");
        } catch (Exception e) {
            basicResponse.setCode(500);
            basicResponse.setMessage("程序错误");
            e.printStackTrace();
        }
        return basicResponse;
    }

    /**
     * 修改访谈
     *
     * @param files
     * @param interview
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/edit")
    public R update(MultipartFile files, @Valid Interview interview, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        R basicResponse = new R();

        try {
            if (interview.getInterviewId() == null || interview.getInterviewId() == 0) {
                basicResponse.setCode(500);
                basicResponse.setMessage("访谈编号不能为空！");
                return basicResponse;
            }
            if (files != null && files.getSize() != 0 && !"0".equals(files.getSize())) {
                String img = FileUploadUtil.upload(files, request);
                if (interview.getType() == "0" || "0".equals(interview.getType())) { //视频
                    interview.setPreVideoUrl(img);
                } else {
                    //图文预告
                    interview.setPrePicUrl(img);
                }
            }
            interviewService.updateInterview(interview);
            basicResponse.setCode(200);
            basicResponse.setMessage("成功");
        } catch (Exception e) {
            basicResponse.setCode(500);
            basicResponse.setMessage("程序错误");
            e.printStackTrace();
        }
        return basicResponse;
    }

    /**
     * 上传视频
     *
     * @param files
     * @param interview
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadVideo")
    public R updateVideo(MultipartFile files, @Valid Interview interview, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        R basicResponse = new R();

        try {
            if (interview.getInterviewId() == null || interview.getInterviewId() == 0) {
                basicResponse.setCode(500);
                basicResponse.setMessage("访谈编号不能为空！");
                return basicResponse;
            }
            if (files != null && files.getSize() != 0 && !"0".equals(files.getSize())) {
                String img = FileUploadUtil.upload(files, request);
                interview.setVideoUrl(img);//视频地址
            }
            interviewService.updateInterview(interview);
            basicResponse.setCode(200);
            basicResponse.setMessage("成功");
        } catch (Exception e) {
            basicResponse.setCode(500);
            basicResponse.setMessage("程序错误");
            e.printStackTrace();
        }
        return basicResponse;
    }


    /**
     * 查询全部访谈记录
     *
     * @param status      0访谈未开始 1 访谈进行中 2 访谈结束',
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    public R findList(String status, Integer currentPage, Integer pageSize) {
        R basicResponse = new R();
        try {
            if (currentPage == null || pageSize == null) {
                basicResponse.setCode(500);
                basicResponse.setMessage("缺少分页参数");
                return basicResponse;
            }
            PageHelper.startPage(currentPage, pageSize);
            PageUtil<InterviewDTO> idto = interviewService.findAll(status, currentPage, pageSize);

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

    /**
     * 查询访谈详情
     *
     * @param id 访谈编号
     * @return
     */
    @RequestMapping("/query")
    public R query(String id) {
        R basicResponse = new R();

        try {
            InterviewDTO idto = interviewService.selectByPrimaryKey(id);
            List<SpeakerDTO> speakerDTOList = interviewService.findByinterviewId(id);
            if (speakerDTOList != null && speakerDTOList.size() > 0) {
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

    /**
     * 获取嘉宾列表
     * @param interviewId 访谈ID
     */
    @GetMapping("/speakers/{interviewId}")
    public R speakersFromInterviewId(@PathVariable("interviewId") String interviewId) {

        List<SpeakerDTO> speakerDTOList = interviewService.findByinterviewId(interviewId);

        return R.ok(speakerDTOList);

    }
}
