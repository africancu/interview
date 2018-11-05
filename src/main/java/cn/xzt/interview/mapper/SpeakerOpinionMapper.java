package cn.xzt.interview.mapper;

import cn.xzt.interview.domain.SpeakerOpinion;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/11/1 17:33
 */
public interface SpeakerOpinionMapper {
    void addspeakerOpinion(SpeakerOpinion speakerOpinion);

    int updateByPrimaryKeySelective(SpeakerOpinion speakerOpinion);

    void romove(String[] array);
}
