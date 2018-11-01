package cn.xzt.interview.DTO;

import lombok.Data;

/**
 * Created by Jay on 2018/11/1 16:46.
 *
 * @author Jay
 */
@Data
public class VisitorDTO {

    private int visitorId;
    private int interviewId;
    private String ip;
    private String name;
}
