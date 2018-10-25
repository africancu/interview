package cn.xzt.interview.mapper;

import cn.xzt.interview.domain.ProhibitWord;

import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 15:35
 */
public interface ProhibitMapper {

    List<ProhibitWord> list(String id);

    Integer add(ProhibitWord word);

    Integer batchAdd(List<String> list);

    void deleteByIds(List<String> list);

    ProhibitWord selectById(Integer id);

    Set<String> getAllWord();


}
