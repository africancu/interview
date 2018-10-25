package cn.xzt.interview.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by zxjaihhl on 2018/10/23.
 */
@Data
public class InterviewPic {
    private Integer picId;
    private Integer interviewId;
    private String picUrl;
    private Date createTime;
    private Date updateTime;
}
