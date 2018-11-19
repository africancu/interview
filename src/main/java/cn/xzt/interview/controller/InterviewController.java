package cn.xzt.interview.controller;

import cn.xzt.interview.DTO.InterviewDTO;
import cn.xzt.interview.DTO.SpeakerDTO;
import cn.xzt.interview.DTO.VisitorDTO;
import cn.xzt.interview.common.constant.ResultStatus;
import cn.xzt.interview.common.utils.FileUploadUtil;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.common.utils.StringUtil;
import cn.xzt.interview.domain.Interview;
import cn.xzt.interview.service.InterviewService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/10/23 11:54
 */
@Controller
@RequestMapping(value = "interview")
@Slf4j
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @Value("${nginx_port}")
    private String port;

    @Value("${physics_url}")
    private String physics_url;

    /**
     * 新增访谈
     *
     * @param file
     * @param interview
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public R save(@RequestParam(value = "file", required = false) MultipartFile file, String[] speakername, @Valid Interview interview, BindingResult bindingResult, HttpServletRequest request) throws Exception {

        String urls = request.getScheme() + "://" + request.getServerName() + ":" + port + "/";
        try {
            if (file != null && !file.isEmpty()) {
                String img = FileUploadUtil.upload(physics_url, file, request);
                if (null != interview.getType() && "0".equals(interview.getType())) {
                    //视频
                    interview.setPreVideoUrl(urls + img);
                } else if (null != interview.getType() && "1".equals(interview.getType())) {
                    //图文预告
                    interview.setPrePicUrl(urls + img);
                } else {
                    return R.error(ResultStatus.PARAM_EMPTY.getCode(), "访谈类型错误");
                }
            }
            interviewService.insertInterview(interview);
            if (null != speakername && speakername.length > 0) {
                for (String num : speakername) {
                    interviewService.insertSpeaker(interview.getInterviewId(), num);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        R ok = R.ok(interview);
        log.info("{}", ok);
        return ok;
    }

    /**
     * 修改访谈
     *
     * @param file
     * @param interview
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public R update(@RequestParam(value = "file", required = false) MultipartFile file, String[] speakername, @Valid Interview interview, BindingResult bindingResult, HttpServletRequest request) throws Exception {

        String urls = request.getScheme() + "://" + request.getServerName() + ":" + port + "/";
        try {
            if (interview.getInterviewId() == null || interview.getInterviewId() == 0) {
                return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
            }
            if (file != null && !file.isEmpty()) {
                String img = FileUploadUtil.upload(physics_url, file, request);
                if (null != interview.getType() && "0".equals(interview.getType())) {
                    //视频
                    interview.setPreVideoUrl(urls + img);
                } else if (null != interview.getType() && "1".equals(interview.getType())) {
                    //图文预告
                    interview.setPrePicUrl(urls + img);
                } else {
                    return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
                }
            }
            interviewService.updateInterview(interview);
            if (null != speakername && speakername.length > 0) {
                interviewService.deleteSpeaker((interview.getInterviewId()).toString());
                for (String num : speakername) {
                    interviewService.insertSpeaker(interview.getInterviewId(), num);
                }
            }
            return R.ok(interview);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(ResultStatus.SERVER_ERROR.getCode(), ResultStatus.SERVER_ERROR.getMessage());
        }
    }

    /**
     * 上传视频
     *
     * @param file
     * @param interview
     * @param bindingResult
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadVideo", method = RequestMethod.POST)
    @ResponseBody
    public R updateVideo(@RequestParam(value = "file", required = false) MultipartFile file, @Valid Interview interview, BindingResult bindingResult, HttpServletRequest request) throws Exception {

        String urls = request.getScheme() + "://" + request.getServerName() + ":" + port + "/";
        try {
            if (null == interview.getInterviewId()) {
                return R.error(ResultStatus.PARAM_EMPTY.getCode(), "访谈编号为空");
            }

            if (file != null && !file.isEmpty()) {
                String img = FileUploadUtil.upload(physics_url, file, request);
                interview.setVideoUrl(urls + img);//视频地址
                interview.setVideoSize(String.valueOf(file.getSize()));
            }
            interviewService.updateInterview(interview);
            return R.ok(interview);
        } catch (Exception e) {

            e.printStackTrace();
            return R.error(ResultStatus.SERVER_ERROR.getCode(), ResultStatus.SERVER_ERROR.getMessage());
        }
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
    @ResponseBody
    public R findList(String status, Integer currentPage, Integer pageSize) {
        R basicResponse = new R();
        try {
            if (currentPage == null || pageSize == null) {
                basicResponse.setCode(600);
                basicResponse.setMsg("缺少分页参数");
                return basicResponse;

            }
            PageHelper.startPage(currentPage, pageSize);
            PageUtil<InterviewDTO> idto = interviewService.findAll(status, currentPage, pageSize);
            if (null != idto.getList() && idto.getList().size() > 0) {
                List<SpeakerDTO> speakerDTOList = null;
                for (InterviewDTO aaa : idto.getList()) {
                    speakerDTOList = interviewService.findByinterviewId(aaa.getInterviewId().toString());
                    if (speakerDTOList != null && speakerDTOList.size() > 0) {
                        aaa.setSpeakerList(speakerDTOList);
                    }
                }
            }

            R ok = R.ok(idto.getList());
            ok.setCount(idto.getTotal());
            return ok;
        } catch (Exception e) {
            basicResponse.setCode(500);
            basicResponse.setMsg("程序错误");
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
    @ResponseBody
    public R query(String id) {
        R basicResponse = new R();

        try {
            if (StringUtil.isBlank(id)) {
                return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
            }
            InterviewDTO idto = interviewService.selectByPrimaryKey(id);
            if (null != idto) {
                List<SpeakerDTO> speakerDTOList = interviewService.findByinterviewId(id);
                if (speakerDTOList != null && speakerDTOList.size() > 0) {
                    idto.setSpeakerList(speakerDTOList);
                }
            }
            basicResponse.setCode(200);
            basicResponse.setMsg("成功");
            basicResponse.setData(idto);
        } catch (Exception e) {
            basicResponse.setCode(500);
            basicResponse.setMsg("程序错误");
            e.printStackTrace();
        }
        return basicResponse;

    }

    /**
     * 获取嘉宾列表
     *
     * @param interviewId 访谈ID
     */
    @GetMapping("/speakers")
    @ResponseBody
    public R speakersFromInterviewId(String interviewId) {

        List<SpeakerDTO> speakerDTOList = interviewService.findByinterviewId(interviewId);

        return R.ok(speakerDTOList);

    }

    /**
     * 获取在线用户列表
     *
     * @param interviewId 访谈ID
     */
    @GetMapping("/visitors")
    @ResponseBody
    public R visitors(int interviewId, int currentPage, int pageSize) {

        PageUtil<VisitorDTO> visitors = interviewService.visitors(interviewId, currentPage, pageSize);

        return R.ok(visitors);

    }
}
