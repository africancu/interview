package cn.xzt.interview.service;

import cn.xzt.interview.domain.SpeakerOpinion;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/11/1 17:45
 */
public interface SpeakerOpinionService {
    void addspeakerOpinion(SpeakerOpinion speakerOpinion);

    int updateByPrimaryKeySelective(SpeakerOpinion speakerOpinion);

    void romove(String[] array);
}
