package cn.xzt.interview.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/10/23 11:36
 */
@Data
public class SpeakerDTO {
    private Integer speakerId;

    private Integer interviewId;

    private String name;

    private Date createTime;

    private Date updateTime;

}
