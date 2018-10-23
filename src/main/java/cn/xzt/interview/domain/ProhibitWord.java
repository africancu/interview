package cn.xzt.interview.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 15:19
 */
@Data
public class ProhibitWord implements Serializable {

    private Integer wordId;

    private String word;

}