package cn.xzt.interview.mapper;

import cn.xzt.interview.domain.Interview;

import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/10/23 10:05
 */
public interface InterviewMapper {
    List<Interview> findAll();
    Interview selectByPrimaryKey();
}
