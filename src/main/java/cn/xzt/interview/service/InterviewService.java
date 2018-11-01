package cn.xzt.interview.service;

import cn.xzt.interview.DTO.InterviewDTO;
import cn.xzt.interview.DTO.SpeakerDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.Interview;

import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/10/23 11:46
 */
public interface InterviewService {

    PageUtil<InterviewDTO> findAll(String status, Integer currentPage, Integer pageSize);
    List<SpeakerDTO> findByinterviewId(String interviewId);
    InterviewDTO selectByPrimaryKey(String interviewId);
    void insertInterview(Interview interview);
    void updateInterview(Interview interview);
    void insertSpeaker(Integer interviewId,String name);
    void deleteSpeaker(String interviewId);
}
