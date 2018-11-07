package cn.xzt.interview.service;

import cn.xzt.interview.DTO.CommentDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.CommentReply;

/**
 * Created by Jay on 2018/10/23 11:23.
 *
 * @author Jay
 */
public interface CommentsService {
    /**
     * 新建一条评论
     *
     * @param interviewId 访谈 ID
     * @param visitorName 游客名
     * @param content     评论内容
     * @param visitorIp   游客IP
     */
    boolean create(int interviewId, String visitorName, String content, String visitorIp);

    /**
     * 查询IP
     *
     * @param id 评论 ID
     * @return 根据评论 ID 查询出 IP
     */
    String selectIpById(int id);

    /**
     * 审核评论
     *
     * @param commentId      评论 ID
     * @param commentContent 评论内容
     * @param replyContent   回复内容
     * @param speakerId      回复者嘉宾 ID
     * @param speakerName    回复者嘉宾名
     */
    boolean audit(int commentId, String commentContent, String replyContent, int speakerId, String speakerName);

    /**
     * 编辑回复评论内容
     *
     * @param commentId      评论 ID
     * @param commentContent 评论内容
     * @param replyId        回复 ID
     * @param replyContent   回复内容
     */
    boolean edit(int commentId, String commentContent, int replyId, String replyContent);

    /**
     * 查询回复列表
     *
     * @param currentPage 当前页数
     * @param pageSize    当前页数据条数
     * @param interviewId 访谈 ID
     */
    PageUtil<CommentReply> comments(int currentPage, int pageSize, int interviewId);

    /**
     * 未审核评论列表
     *
     * @param interviewId 访谈 ID
     */
    PageUtil<CommentDTO> selectByInterviewId(int currentPage, int pageSize, int interviewId);

    /**
     * 删除评论与回复记录
     *
     * @param commentIds 评论Ids
     * @param replyIds   回复Ids
     */
    boolean delete(Integer[] commentIds, Integer[] replyIds);
}
