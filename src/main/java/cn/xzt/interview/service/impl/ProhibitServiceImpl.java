package cn.xzt.interview.service.impl;

import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.domain.ProhibitWord;
import cn.xzt.interview.mapper.ProhibitMapper;
import cn.xzt.interview.service.ProhibitService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 15:02
 */
@Service
public class ProhibitServiceImpl implements ProhibitService {
    @Autowired
    private ProhibitMapper prohibitMapper;

    @Override
    public PageUtil<ProhibitWord> list(Integer pageNum, Integer pageSize, String key) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProhibitWord> list = prohibitMapper.list(key);
        return new PageUtil<>(list);
    }

    @Override
    public ProhibitWord create(ProhibitWord word) {
        prohibitMapper.add(word);
        ProhibitWord prohibitWordResult = prohibitMapper.selectById(word.getWordId());
        return prohibitWordResult;
    }

    @Override
    public void remove(List<String> list) {

        prohibitMapper.deleteByIds(list);

    }


}