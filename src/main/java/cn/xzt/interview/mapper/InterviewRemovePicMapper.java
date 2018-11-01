package cn.xzt.interview.mapper;


import cn.xzt.interview.domain.InterviewPic;
import cn.xzt.interview.vo.GetInterviewPicVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * Created by zxjaihhl on 2018/10/23.
 */
public interface InterviewRemovePicMapper  {

    /**
     * 删除图片
     */
     void removePic(InterviewPic interviewPic);

    /**
     * 删除图片
     */
    void removePics(List<String> list);


}
