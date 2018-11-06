package cn.xzt.interview.mapper;

import cn.xzt.interview.DTO.SpeakerOpinionDTO;
import cn.xzt.interview.domain.SpeakerOpinion;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.URL;

import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/11/1 17:33
 */
public interface SpeakerOpinionMapper {
    void addspeakerOpinion(SpeakerOpinion speakerOpinion);

    int updateByPrimaryKeySelective(SpeakerOpinion speakerOpinion);

    void romove(String[] array);

    List<SpeakerOpinionDTO> findAll(@Param("interviewId") String interviewId);
}
