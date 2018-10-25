package cn.xzt.interview.controller;

import cn.xzt.interview.common.constant.ResultStatus;

import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.domain.InterviewPic;

import cn.xzt.interview.service.InterviewService;
import cn.xzt.interview.vo.GetInterviewPicVO;
import lombok.extern.slf4j.Slf4j;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

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
    private InterviewService interviewService;

    /**
     * 删除访谈
     *
     * @return
     */
    @RequestMapping("/remove")
    public R removeInterview(@RequestParam(value = "id") Integer id) {
        R r = new R();
        if (id != null) {
            interviewService.removeInterview(id);
            r.setCode(ResultStatus.OK.getCode());
            r.setMessage(ResultStatus.OK.getMessage());
            return r;
        } else {
            r.setCode(ResultStatus.BAD_REQUEST.getCode());
            r.setMessage(ResultStatus.BAD_REQUEST.getMessage());
            return r;
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
        List<InterviewPic> interviewPicList = interviewService.getImages(getInterviewPicVO);
        if (interviewPicList != null && interviewPicList.size() > 0) {
            r.setCode(ResultStatus.OK.getCode());
            r.setMessage(ResultStatus.OK.getMessage());
            r.setResult(interviewPicList);
            return r;
        } else {
            r.setCode(ResultStatus.BAD_REQUEST.getCode());
            r.setMessage(ResultStatus.BAD_REQUEST.getMessage());
            return r;
        }
    }

    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping("/uploadImages")
    public R uploadImages(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws IOException {
        R r = new R();
        //获取访谈ID
        String interviewId = request.getParameter("interviewId");
        // 盘符地址
        String disk = "D:\\";
        // 网络地址
        String httpUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        // 文件上传根目录
        String uploadRoot = "fileroot";
        // 根目录下存放文件的相对目录
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        String relativePath = format + "\\" + interviewId + "\\";
        // 文件的存放目录
        String dir = disk + uploadRoot + "\\" + relativePath;
        try {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String fileFullName = file.getOriginalFilename();
                    InterviewPic interviewPic = new InterviewPic();
                    interviewPic.setInterviewId(Integer.valueOf(interviewId).intValue());
                    interviewPic.setPicUrl(httpUrl + uploadRoot + "/" + relativePath.replace("\\", "/") + fileFullName);
                    FileUtils.copyInputStreamToFile(file.getInputStream(), new File(dir + fileFullName));
                    interviewService.loadPic(interviewPic);
                }

            }
            r.setCode(ResultStatus.OK.getCode());
            r.setMessage(ResultStatus.OK.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            r.setCode(ResultStatus.SERVER_ERROR.getCode());
            r.setMessage(ResultStatus.SERVER_ERROR.getMessage());
        }
        return r;
    }

    /**
     * 删除图片
     */
    @RequestMapping("/removePic")
    public R removePic(@RequestBody List<InterviewPic> interviewPicList) {
        R r = new R();
        for(InterviewPic interviewPic:interviewPicList){
            interviewService.removePic(interviewPic);
        }
        r.setCode(ResultStatus.OK.getCode());
        r.setMessage(ResultStatus.OK.getMessage());
       return r;
    }

}
