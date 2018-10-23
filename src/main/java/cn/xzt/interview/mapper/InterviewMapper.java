package cn.xzt.interview.mapper;

import cn.xzt.interview.domain.Interview;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/10/23 10:05
 */
public interface InterviewMapper {
    List<Interview> findAll(@Param("status") String status);
    Interview selectByPrimaryKey(@Param("interviewId") String interviewId);
    void insertInterview(Interview interview);
    void updateInterview(Interview interview);
}
