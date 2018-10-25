package cn.xzt.interview.service;

import cn.xzt.interview.DTO.BlacklistDTO;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.Blacklist;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 11:05
 */
public interface BlacklistService {

    PageUtil<BlacklistDTO> list(Integer currentPage, Integer pageSize);

    Blacklist create(Blacklist blacklist);

    Blacklist selectByIp(String ip);

    void remove(Integer id);


}
