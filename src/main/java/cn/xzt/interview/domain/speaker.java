package cn.xzt.interview.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/10/23 11:23
 * 嘉宾表
 */
@Data
public class speaker {
    private Integer speakerId;

    private Integer interviewId;

    private String name;

    private Date createTime;

    private Date updateTime;

}
