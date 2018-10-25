package cn.xzt.interview.mapper;


import cn.xzt.interview.domain.InterviewPic;
import cn.xzt.interview.vo.GetInterviewPicVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zxjaihhl on 2018/10/23.
 */
public interface InterviewRemoveMapper {
    /**
     * 删除访谈
     * @param id
     */
    void removeInterview(Integer id);


}
