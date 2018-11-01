package cn.xzt.interview.mapper;

import cn.xzt.interview.DTO.VisitorDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Jay on 2018/11/1 16:56.
 *
 * @author Jay
 */
public interface VisitorMapper {

    List<VisitorDTO> findByInterviewId(@Param("interviewId") int interviewId);
}
