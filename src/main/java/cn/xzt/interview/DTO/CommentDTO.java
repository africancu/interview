package cn.xzt.interview.DTO;

import lombok.Data;

/**
 * Created by Jay on 2018/10/23 16:56.
 *
 * @author Jay
 */
@Data
public class CommentDTO {

    private int commentId;
    private int interviewId;
    private String visitor;
    private String visitorIp;
    private String content;
    /**
     * 0：未审核；1：审核通过 （默认为0：未审核）
     */
    private int status;
    private String createTime;
    private String updateTime;
}
