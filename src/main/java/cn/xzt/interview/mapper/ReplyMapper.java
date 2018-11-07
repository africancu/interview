package cn.xzt.interview.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by Jay on 2018/10/23 16:05.
 *
 * @author Jay
 */
@Component
public interface ReplyMapper {


    int insert(@Param("commentId") int commentId, @Param("content") String content, @Param("speakerId") int speakerId, @Param("speakerName") String speakerName);

    int updateContent(@Param("id") int id, @Param("content") String content);

    int deleteById(Integer[] replyIds);
}
