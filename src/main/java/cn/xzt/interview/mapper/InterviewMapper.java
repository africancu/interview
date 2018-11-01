package cn.xzt.interview.mapper;

import cn.xzt.interview.DTO.InterviewDTO;
import cn.xzt.interview.DTO.SpeakerDTO;
import cn.xzt.interview.domain.Interview;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/10/23 10:05
 */
public interface InterviewMapper {
    List<InterviewDTO> findAll(@Param("status") String status);
    List<SpeakerDTO> findByinterviewId(@Param("interviewId") String interviewId);
    InterviewDTO selectByPrimaryKey(@Param("interviewId") String interviewId);
    void insertInterview(Interview interview);
    void updateInterview(Interview interview);

    /**
     * 新增访谈嘉宾
     * @param interviewId
     * @param name
     */
    void insertSpeaker(@Param("interviewId") Integer interviewId,@Param("name") String name);
    void deleteSpeaker(@Param("interviewId")  String interviewId);
}
