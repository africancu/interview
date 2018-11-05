package cn.xzt.interview.service.impl;

import cn.xzt.interview.domain.SpeakerOpinion;
import cn.xzt.interview.mapper.SpeakerOpinionMapper;
import cn.xzt.interview.service.SpeakerOpinionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/11/1 17:45
 */

@Service(value = "SpeakerOpinionService")
@Slf4j
public class SpeakerOpinionServiceImpl implements SpeakerOpinionService {
    @Autowired
    private SpeakerOpinionMapper speakerOpinionMapper;

    @Override
    public void addspeakerOpinion(SpeakerOpinion speakerOpinion) {
        speakerOpinionMapper.addspeakerOpinion(speakerOpinion);
    }


    @Override
    public int updateByPrimaryKeySelective(SpeakerOpinion speakerOpinion) {
        return speakerOpinionMapper.updateByPrimaryKeySelective(speakerOpinion);
    }

    @Override
    public void romove(String[] array) {
        speakerOpinionMapper.romove(array);
    }
}
