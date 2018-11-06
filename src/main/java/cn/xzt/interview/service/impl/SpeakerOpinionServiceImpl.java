package cn.xzt.interview.service.impl;

import cn.xzt.interview.DTO.SpeakerOpinionDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.SpeakerOpinion;
import cn.xzt.interview.mapper.SpeakerOpinionMapper;
import cn.xzt.interview.service.SpeakerOpinionService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/11/1 17:45
 */

@Service
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

    @Override
    public PageUtil<SpeakerOpinionDTO> findAll(String interviewId,Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<SpeakerOpinionDTO> list= speakerOpinionMapper.findAll(interviewId);
        return new PageUtil<>(list);
    }
}
