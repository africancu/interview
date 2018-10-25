package cn.xzt.interview.mapper;

import cn.xzt.interview.DTO.CommentDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Jay on 2018/10/23 11:29.
 *
 * @author Jay
 */
@Component
public interface CommentMapper {


    int insert(@Param("interviewId") int interviewId, @Param("visitorName") String visitorName, @Param("content") String content, @Param("visitorIp") String visitorIp);

    String selectIp(@Param("id") int id);

    int updateContent(@Param("id") int commentId, @Param("content") String content,@Param("status") int status);

    List<CommentDTO> selectByInterviewId(@Param("interviewId") int interviewId);

}
