package cn.xzt.interview.service.impl;

import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.ProhibitWord;
import cn.xzt.interview.mapper.ProhibitMapper;
import cn.xzt.interview.service.ProhibitService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;


/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 15:02
 */
@Service(value = "ProhibitService")
@Slf4j
public class ProhibitServiceImpl implements ProhibitService {
    @Autowired
    private ProhibitMapper prohibitMapper;

    /**
     * 查询违禁词列表
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param key
     * @return
     */
    @Override
    public PageUtil<ProhibitWord> list(Integer pageNum, Integer pageSize, String key) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProhibitWord> list = prohibitMapper.list(key);
        return new PageUtil<>(list);
    }

    /**
     * 单个添加违禁词
     *
     * @param word
     * @return
     */
    @Override
    public ProhibitWord create(ProhibitWord word) {
        prohibitMapper.add(word);
        ProhibitWord prohibitWordResult = prohibitMapper.selectById(word.getWordId());
        return prohibitWordResult;
    }

    /**
     * 批量删除违禁词
     *
     * @param list
     */
    @Override
    public void remove(List<String> list) {
        prohibitMapper.deleteByIds(list);
    }

    /**
     * 批量导入违禁词
     *
     * @param list
     */
    @Override
    public void batchImport(List<String> list) {
        prohibitMapper.batchAdd(list);
    }


    @Override
    public Set<String> getAllWord() {
        Set<String> allWord = prohibitMapper.getAllWord();
        return allWord;
    }
}