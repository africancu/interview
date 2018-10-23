package cn.xzt.interview.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 14:06
 */
@Data
public class Blacklist {

    private Integer blacklistId;

    private Integer interviewId;

    private String ip;

    private String visitor;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public enum Status{
        ABLE(0, "启用"),
        DISABLE(1, "屏蔽");

        private Integer code;
        private String message;

        private Status(Integer code, String message){
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

}