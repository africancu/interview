package cn.xzt.interview.service;

import cn.xzt.interview.domain.InterviewPic;
import cn.xzt.interview.vo.GetInterviewPicVO;

import java.util.List;

/**
 * Created by zxjaihhl on 2018/10/23.
 */
public interface InterviewService {
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

}
