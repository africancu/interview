package cn.xzt.interview.service.impl;

import cn.xzt.interview.DTO.InterviewDTO;
import cn.xzt.interview.DTO.SpeakerDTO;
import cn.xzt.interview.DTO.VisitorDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.Interview;
import cn.xzt.interview.mapper.InterviewMapper;
import cn.xzt.interview.mapper.VisitorMapper;
import cn.xzt.interview.service.InterviewService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: lyj
 * @Date: 2018/10/23 11:46
 */
@Service(value = "InterviewService")
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private InterviewMapper interviewMapper;

    @Autowired
    private VisitorMapper visitorMapper;


    @Override
    public PageUtil<InterviewDTO> findAll(String status, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<InterviewDTO> list=interviewMapper.findAll(status);
        return new PageUtil<>(list);
    }

    @Override
    public List<SpeakerDTO> findByinterviewId(String interviewId) {
        return interviewMapper.findByinterviewId(interviewId);
    }

    @Override
    public InterviewDTO selectByPrimaryKey(String interviewId) {
        return interviewMapper.selectByPrimaryKey(interviewId);

    }

    @Override
    public void insertInterview(Interview interview) {
        interviewMapper.insertInterview(interview);
    }

    @Override
    public void updateInterview(Interview interview) {
        interviewMapper.updateInterview(interview);
    }

    @Override
    public void insertSpeaker(Integer interviewId, String name) {
        interviewMapper.insertSpeaker(interviewId,name);
    }

    @Override
    public void deleteSpeaker(String interviewId) {
        interviewMapper.deleteSpeaker( interviewId);
    }

    @Override
    public List<VisitorDTO> visitors(int interviewId) {

        return visitorMapper.findByInterviewId(interviewId);
    }
}
