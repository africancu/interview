package cn.xzt.interview.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/11/5 17:02
 * 文字实录
 */

@Data
public class SpeakerOpinionDTO {

    private Integer opinionId;

    private Integer interviewId;

    /**
     * 嘉宾编号
     */
    private Integer speakerId;

    private String speakerName;

    private String content;

    /**
     * 0 启用  1 删除
     */
    private Integer enable;

    private Date createTime;
}
