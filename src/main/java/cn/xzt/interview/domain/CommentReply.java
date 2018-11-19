package cn.xzt.interview.domain;

import cn.xzt.interview.DTO.CommentDTO;
import cn.xzt.interview.DTO.ReplyDTO;
import lombok.Data;


/**
 * Created by Jay on 2018/10/23 17:14.
 *
 * @author Jay
 */
@Data
public class CommentReply {

    private CommentDTO comment;
    private ReplyDTO reply;

}


