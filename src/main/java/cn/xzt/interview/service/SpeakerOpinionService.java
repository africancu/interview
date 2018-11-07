package cn.xzt.interview.service;

import cn.xzt.interview.DTO.SpeakerOpinionDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.SpeakerOpinion;

import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/11/1 17:45
 */
public interface SpeakerOpinionService {
    void addSpeakerOpinion(SpeakerOpinion speakerOpinion);

    int updateByPrimaryKeySelective(SpeakerOpinion speakerOpinion);

    void remove(Integer[] ids);

    PageUtil<SpeakerOpinionDTO> findAll(String interviewId, Integer currentPage, Integer pageSize);
}
