package cn.xzt.interview.service.impl;


import cn.xzt.interview.domain.InterviewPic;
import cn.xzt.interview.mapper.InterviewGetPicMapper;
import cn.xzt.interview.mapper.InterviewLoadPicMapper;
import cn.xzt.interview.mapper.InterviewRemoveMapper;
import cn.xzt.interview.mapper.InterviewRemovePicMapper;
import cn.xzt.interview.service.InterviewPicService;
import cn.xzt.interview.vo.GetInterviewPicVO;
import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <<<<<<< HEAD
 * Created by zxjaihhl on 2018/10/23.
 */
@Service
public class InterviewPicServiceImpl implements InterviewPicService {
    @Autowired
    private InterviewRemoveMapper interviewRemoveMapper;

    @Autowired
    private InterviewGetPicMapper interviewGetPicMapper;

    @Autowired
    private InterviewLoadPicMapper interviewLoadPicMapper;

    @Autowired
    private InterviewRemovePicMapper interviewRemovePicMapper;

    @Override
    public void removeInterview(Integer id) {
        interviewRemoveMapper.removeInterview(id);
    }

    @Override
    public List<InterviewPic> getImages(GetInterviewPicVO getInterviewPicVO) {
        if (getInterviewPicVO.getInterviewId() != null) {
            Integer pageNum = getInterviewPicVO.getPageNum();
            Integer pageSize = getInterviewPicVO.getPageSize();
            Page<InterviewPic> page = new Page<>(pageNum, pageSize);

            List<InterviewPic> interviewPicList = interviewGetPicMapper.getImages(page, getInterviewPicVO);
            return interviewPicList;
        }
        return null;

    }

    @Override
    public void loadPic(InterviewPic interviewPic) {
        if (interviewPic != null) {
            interviewLoadPicMapper.loadPic(interviewPic);
        }
    }

    @Override

    public void removePic(InterviewPic interviewPic) {
        if (interviewPic.getPicId() != null) {
            interviewRemovePicMapper.removePic(interviewPic);
        }
    }


}
