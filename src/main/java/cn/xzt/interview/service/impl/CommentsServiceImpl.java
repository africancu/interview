package cn.xzt.interview.service.impl;

import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.StringUtil;
import cn.xzt.interview.domain.CommentReply;
import cn.xzt.interview.mapper.CommentMapper;
import cn.xzt.interview.mapper.CommentReplyMapper;
import cn.xzt.interview.mapper.ReplyMapper;
import cn.xzt.interview.service.CommentsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jay on 2018/10/23 11:24.
 *
 * @author Jay
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    private CommentMapper mCommentMapper;
    private ReplyMapper mReplyMapper;
    private CommentReplyMapper mCommentReplyMapper;

    @Autowired
    public CommentsServiceImpl(CommentMapper commentMapper, ReplyMapper replyMapper, CommentReplyMapper commentReplyMapper) {
        mCommentMapper = commentMapper;
        mReplyMapper = replyMapper;
        mCommentReplyMapper = commentReplyMapper;
    }


    @Override
    public int create(int interviewId, String visitorName, String content, String visitorIp) {
        return mCommentMapper.insert(interviewId, visitorName, content, visitorIp);
    }

    @Override
    public String selectIpById(int id) {
        return mCommentMapper.selectIp(id);
    }

    @Override
    public boolean audit(int commentId, String commentContent, String replyContent, int speakerId) {

        if (!StringUtil.isBlank(commentContent)) {
            // 审核通过需要将评论的状态置为1，表示审核通过
            final int commentIndex = mCommentMapper.updateContent(commentId, commentContent, 1);
            final int replyIndex = mReplyMapper.insert(commentId, replyContent, speakerId);

            return commentIndex > 0 && replyIndex > 0;
        } else {
            final int replyIndex = mReplyMapper.insert(commentId, replyContent, speakerId);
            return replyIndex > 0;
        }

    }

    @Override
    public boolean edit(int commentId, String commentContent, int replyId, String replyContent) {
        // 编辑时，评论的状态仍然是1
        final int commentIndex = mCommentMapper.updateContent(commentId, commentContent, 1);
        final int replyIndex = mReplyMapper.updateContent(replyId, replyContent);

        return commentIndex > 0 && replyIndex > 0;
    }

    @Override
    public PageUtil<CommentReply> comments(int currentPage, int pageSize, int interviewId) {

        PageHelper.startPage(currentPage, pageSize);

        List<CommentReply> comments = mCommentReplyMapper.comments(interviewId);

        return new PageUtil<>(comments);
    }
}