package cn.xzt.interview.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/10/23 11:37
 */
@Data
public class InterviewDTO {
    List<SpeakerDTO> speakerList;

    private Integer interviewId;

    //访谈分类编号
    private Integer categoryId;

    //访谈名称
    private String name;

    private Date beginTime;

    private Date endTime;

    //主持人
    private String compere;

    //描述
    private String description;

    //0视频直播，1图文直播

    private String type;

    //0访谈未开始1 访谈进行中 2 访谈结束
    private String status;

    //预告图片地址
    private String prePicUrl;

    //预告视频地址
    private String preVideoUrl;

    //视频地址
    private String videoUrl;

    private Date createTime;

    private Date updateTime;
}
