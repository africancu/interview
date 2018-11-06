package cn.xzt.interview.controller;

import cn.xzt.interview.common.constant.ResultStatus;

import cn.xzt.interview.common.utils.*;
import cn.xzt.interview.domain.InterviewPic;

import cn.xzt.interview.service.InterviewPicService;
import cn.xzt.interview.vo.GetInterviewPicVO;
import cn.xzt.interview.vo.RemoveInterviewPicVO;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by zxjaihhl on 2018/10/23.
 */
@RestController
@RequestMapping(value = "interview")
@Slf4j
public class InterviewPicController {

    @Autowired
    private InterviewPicService interviewPicService;


    @Value("${physics_url}")
    private String physics_url;
    @Value("${nginx_port}")
    private String port;

    /**
     * 删除访谈
     *
     * @return
     */
    @RequestMapping("/remove")
    public R removeInterview(@RequestParam(value = "id") Integer id) {
        if (id != null) {
            interviewPicService.removeInterview(id);
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 查询图片
     *
     * @param getInterviewPicVO
     * @return
     */
    @RequestMapping("/images")
    public R getImages(@RequestBody GetInterviewPicVO getInterviewPicVO) {
        R r = new R();
        PageUtil<InterviewPic> interviewPicList = interviewPicService.getImages(getInterviewPicVO);
        if (interviewPicList != null ) {
            return R.ok(interviewPicList);
        } else {
            return R.error();
        }
    }

    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping("/uploadImages")
    public R uploadImages(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws IOException {
        //获取访谈ID
        String interviewId = request.getParameter("interviewId");
        Integer interviewIdTwo = Integer.valueOf(interviewId).intValue();
        String urls=request.getScheme()+ "://" + request.getServerName()+":"+port+"/";
        if (files == null) {
            return R.error();
        }
        try {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    //判断图片格式
                    String fileName = file.getOriginalFilename();
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    if (suffix.equals(".png") || suffix.equals(".PNG") || suffix.equals(".jpg") || suffix.equals(".JPG")) {
                        String uploadUrl = FileUploadUtil.upload(physics_url, file, request);
                        InterviewPic interviewPic = new InterviewPic();
                        interviewPic.setInterviewId(interviewIdTwo);
                        interviewPic.setPicUrl(urls + uploadUrl);
                        interviewPicService.loadPic(interviewPic);
                    } else {
                        return R.error(100, "格式错误");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();

    }

    /**
     * 删除图片
     */
    @RequestMapping("/removePic")
    public R removePic(@RequestBody RemoveInterviewPicVO removeInterviewPicVO) {
        List<Integer> picIds = removeInterviewPicVO.getPicIds();
        if (picIds == null && picIds.size() == 0) {
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        for (Integer picId : picIds) {
            interviewPicService.removePic(picId);
        }
        return R.ok();
    }


}
