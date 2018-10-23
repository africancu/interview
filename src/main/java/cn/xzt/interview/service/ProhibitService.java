package cn.xzt.interview.service;

import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.ProhibitWord;

import java.util.List;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 15:02
 */
public interface ProhibitService {

    PageUtil<ProhibitWord> list(Integer pageNum, Integer pageSize, String key);

    ProhibitWord create(ProhibitWord word);

    void remove(List<String> list);

}
