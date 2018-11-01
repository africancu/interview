package cn.xzt.interview.service;


import cn.xzt.interview.domain.InterviewPic;
import cn.xzt.interview.vo.GetInterviewPicVO;

import cn.xzt.interview.DTO.InterviewDTO;
import cn.xzt.interview.DTO.SpeakerDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.Interview;


import java.util.List;

/**
<<<<<<< HEAD
 * Created by zxjaihhl on 2018/10/23.
 */
public interface InterviewPicService {
    /**
     * 删除访谈
     * @param id
     */
    void removeInterview(Integer id);

    /**
     * 查询图片
     */
    List<InterviewPic> getImages(GetInterviewPicVO getInterviewPicVO);


    /**
     * 上传图片
     */
    void loadPic(InterviewPic interviewPic);

    /**
     * 删除图片
     */
    void removePic(InterviewPic interviewPic);

    /**
     * 删除图片
     */
    void removePics(List<String> list);
}
