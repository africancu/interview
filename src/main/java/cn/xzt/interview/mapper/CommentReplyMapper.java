package cn.xzt.interview.mapper;

import cn.xzt.interview.domain.CommentReply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Jay on 2018/10/24 15:11.
 *
 * @author Jay
 */
@Component
public interface CommentReplyMapper {


    List<CommentReply> comments(@Param("interviewId") int interviewId);
}
