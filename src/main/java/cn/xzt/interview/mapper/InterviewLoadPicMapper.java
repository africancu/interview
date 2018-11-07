package cn.xzt.interview.mapper;

import cn.xzt.interview.domain.InterviewPic;

/**
 * Created by zxjaihhl on 2018/10/24.
 */
public interface InterviewLoadPicMapper {
    void loadPic(InterviewPic interviewPic);
    InterviewPic getPicTime(String picUrl);
}
