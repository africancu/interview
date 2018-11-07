package cn.xzt.interview.DTO;

import lombok.Data;

/**
 * Created by Jay on 2018/10/23 16:59.
 *
 * @author Jay
 */
@Data
public class ReplyDTO {
    private int replyId;
    private int commentId;
    private int interviewId;
    private String content;
    private int speakerId;
    private String speakerName;
    private String createTime;
    private String updateTime;
}
