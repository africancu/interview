package cn.xzt.interview.controller;

import cn.xzt.interview.DTO.CommentDTO;
import cn.xzt.interview.common.constant.ResultStatus;
import cn.xzt.interview.common.utils.IpUtil;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.ParamCheckUtil;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.domain.Blacklist;
import cn.xzt.interview.domain.CommentReply;
import cn.xzt.interview.service.BlacklistService;
import cn.xzt.interview.service.CommentsService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;


/**
 * Created by Jay on 2018/10/23 09:42.
 *
 * @author Jay
 */
@RequestMapping("/comments")
@RestController
public class CommentsController {
    private CommentsService mCommentsService;
    private BlacklistService mBlacklistService;

    @Autowired
    public CommentsController(CommentsService commentsService, BlacklistService blacklistService) {
        mCommentsService = commentsService;
        mBlacklistService = blacklistService;
    }


    /**
     * 游客提交一条评论
     *
     * @param params {
     *               "interviewId" : 访谈ID，
     *               "visitorName" : 游客昵称,
     *               "content"     : 评论内容
     *               }
     */
    @PostMapping("/create")
    public R create(HttpServletRequest request, @RequestBody String params) {
        R response = ParamCheckUtil.checkPrams(params, "interviewId", "visitor", "content");
        if (response != null) {
            return response;
        }

        JSONObject jsonObject = JSONObject.parseObject(params);

        final int interviewId = jsonObject.getIntValue("interviewId");
        final String visitorName = jsonObject.getString("visitor");
        final String content = jsonObject.getString("content");

        // 先获取 IP 地址检测下是否被封IP
        final String ip = IpUtil.getIpAddr(request);

        Blacklist blacklist = mBlacklistService.selectByIp(ip);

        if (blacklist != null &&
                blacklist.getStatus().equals(Blacklist.Status.DISABLE.getCode())) {

            if (blacklist.getInterviewId() == null || blacklist.getInterviewId() == interviewId) {
                return R.error(ResultStatus.ERROR.getCode(), "你已经被禁止评论");
            }
        }

        boolean result = mCommentsService.create(interviewId, visitorName, content, ip);

        if (result) {
            return R.ok();
        }

        return R.error(ResultStatus.ERROR.getCode(), ResultStatus.ERROR.getMessage());

    }

    /**
     * 审核一条评论
     *
     * @param params {
     *               "interviewId" : 访谈ID,
     *               "commentId" : 评论ID,
     *               "commentContent" : 评论内容,
     *               "replyContent" : 回复内容,
     *               "speakerId" : 嘉宾ID
     *               }
     */
    @PostMapping("/audit")
    public R audit(@RequestBody String params) {
        R response = ParamCheckUtil.checkPrams(params, "commentId", "replyContent", "speakerId");
        if (response != null) {
            return response;
        }

        JSONObject jsonObject = JSONObject.parseObject(params);

        final int commentId = jsonObject.getIntValue("commentId");
        final String commentContent = jsonObject.getString("commentContent");
        final String replyContent = jsonObject.getString("replyContent");
        final int speakerId = jsonObject.getIntValue("speakerId");

        final boolean auditResult = mCommentsService.audit(commentId, commentContent, replyContent, speakerId);

        if (auditResult) {
            return R.ok();
        }

        return R.error(ResultStatus.ERROR.getCode(), ResultStatus.ERROR.getMessage());
    }

    /**
     * 获取未审核评论
     *
     * @param interviewId 访谈ID
     * @param currentPage 当前页码
     * @param pageSize    当前页数据条数
     */
    @GetMapping("/noaudit")
    public R noaudit(@RequestParam int interviewId, @RequestParam int currentPage, @RequestParam int pageSize) {
        PageUtil<CommentDTO> comments = mCommentsService.selectByInterviewId(currentPage, pageSize, interviewId);
        return R.ok(comments);
    }

    /**
     * 根据访谈ID获取评论列表
     *
     * @param interviewId 访谈ID
     * @param currentPage 当前页码
     * @param pageSize    当前页数据条数
     */
    @GetMapping
    public R comments(@RequestParam int interviewId, @RequestParam int currentPage, @RequestParam int pageSize) {
        PageUtil<CommentReply> comments = mCommentsService.comments(currentPage, pageSize, interviewId);

        return R.ok(comments);
    }

    /**
     * 编辑评论和回复内容
     *
     * @param params{ "commentId" : 评论ID,
     *                "replyId" : 回复ID,
     *                "commentContent" : 评论内容,
     *                "replyContent" : 回复内容
     *                }
     */
    @PostMapping("/edit")
    public R edit(@RequestBody String params) {

        R response = ParamCheckUtil.checkPrams(params, "commentId", "replyId", "commentContent", "replyContent");
        if (response != null) {
            return response;
        }

        JSONObject jsonObject = JSONObject.parseObject(params);

        final int commentId = jsonObject.getIntValue("commentId");
        final String commentContent = jsonObject.getString("commentContent");

        final int replyId = jsonObject.getIntValue("replyId");
        final String replyContent = jsonObject.getString("replyContent");

        final boolean editResult = mCommentsService.edit(commentId, commentContent, replyId, replyContent);

        if (editResult) {
            return R.ok();
        }

        return R.error(ResultStatus.ERROR.getCode(), ResultStatus.ERROR.getMessage());

    }

    /**
     * 禁用某个IP用户在对应的访谈中评论权限
     *
     * @param params {
     *               "commentId" : 评论ID,
     *               "interviewId" : 访谈ID
     *               }
     */
    @PostMapping("/disable")
    public R disable(@RequestBody String params) {
        R response = ParamCheckUtil.checkPrams(params, "commentId", "interviewId", "visitor");
        if (response != null) {
            return response;
        }
        JSONObject jsonObject = JSONObject.parseObject(params);
        final int commentId = jsonObject.getIntValue("commentId");
        final int interviewId = jsonObject.getIntValue("interviewId");
        final String visitorName = jsonObject.getString("visitor");

        final String visitorIp = mCommentsService.selectIpById(commentId);

        Blacklist blacklist = new Blacklist();

        blacklist.setInterviewId(interviewId);
        blacklist.setIp(visitorIp);
        blacklist.setVisitor(visitorName);

        Blacklist blacklistResult = mBlacklistService.create(blacklist);
        if (blacklistResult == null) {

            return R.error(ResultStatus.ERROR.getCode(), ResultStatus.ERROR.getMessage());
        }

        Integer status = blacklistResult.getStatus();

        if (!status.equals(Blacklist.Status.DISABLE.getCode())) {
            // 如果状态为非禁用状态，表示操作失败
            return R.error(ResultStatus.ERROR.getCode(), ResultStatus.ERROR.getMessage());
        }

        return R.ok();
    }

    /**
     * 删除评论与回复记录
     * @param params
     * {
     *     "commentIds" : 评论 ID 集合,
     *     "replyIds" : 回复 ID 集合
     * }
     */
    @PostMapping("/delete")
    public R delete(@RequestBody String params) {
        R response = ParamCheckUtil.checkPrams(params, "commentIds");
        if (response != null) {
            return response;
        }
        JSONObject jsonObject = JSONObject.parseObject(params);
        final JSONArray commentIdsArray = jsonObject.getJSONArray("commentIds");
        final JSONArray replyIdsArray = jsonObject.getJSONArray("replyIds");

        if (commentIdsArray.size() == 0) {
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), "commentIds 参数为空");
        }


        Integer[] commentArray = new Integer[commentIdsArray.size()];
        Integer[] commentIds = commentIdsArray.toArray(commentArray);

        Integer[] replyArray = new Integer[replyIdsArray.size()];
        Integer[] replyIds = commentIdsArray.toArray(replyArray);



        final boolean deleteResult = mCommentsService.delete(commentIds, replyIds);
        if (deleteResult) {
            return R.ok();
        }

        return R.error(ResultStatus.ERROR.getCode(), ResultStatus.ERROR.getMessage());
    }
}
