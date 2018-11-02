package cn.xzt.interview.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/11/1 17:32
 * 文字实录
 */

@Data
public class SpeakerOpinion {
    private Integer opinionId;

    //嘉宾编号
    private Integer speakerId;

    private String content;

    //0 启用  1 删除
    private Integer enable;

    private Date createTime;

    private Date updateTime;

}
