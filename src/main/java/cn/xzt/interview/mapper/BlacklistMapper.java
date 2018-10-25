package cn.xzt.interview.mapper;

import cn.xzt.interview.DTO.BlacklistDTO;
import cn.xzt.interview.domain.Blacklist;

import java.util.List;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 11:05
 */
public interface BlacklistMapper {

    List<BlacklistDTO> list();

    Integer create(Blacklist blacklist);


    Blacklist selectBlacklistById(Integer id);

    void remove(Integer id);

    Blacklist selectByIp(String ip);
}
