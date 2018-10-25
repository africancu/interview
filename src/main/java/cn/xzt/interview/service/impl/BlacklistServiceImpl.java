package cn.xzt.interview.service.impl;

import cn.xzt.interview.DTO.BlacklistDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.Blacklist;
import cn.xzt.interview.mapper.BlacklistMapper;
import cn.xzt.interview.service.BlacklistService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 11:05
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {
    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public PageUtil<BlacklistDTO> list(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<BlacklistDTO> list = blacklistMapper.list();
        return new PageUtil<>(list);
    }

    @Override
    public Blacklist create(Blacklist blacklist) {
        Integer integer = blacklistMapper.create(blacklist);
        Blacklist blacklist_result = blacklistMapper.selectBlacklistById(blacklist.getBlacklistId());
        return blacklist_result;
    }

    @Override
    public Blacklist selectByIp(String ip) {
        return blacklistMapper.selectByIp(ip);
    }


    @Override
    public void remove(Integer id) {
        blacklistMapper.remove(id);
    }
}