package cn.xzt.interview.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 11:12
 */
@Data
public class BlacklistDTO {
    private Integer blacklistId;
    private Integer interviewId;
    private String ip;
    private String visitor = "游客";
    private char status;
    private Date createTime;
    private Date updateTime;
}