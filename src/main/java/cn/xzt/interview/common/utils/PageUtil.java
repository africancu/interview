package cn.xzt.interview.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

import java.util.List;

/**
 * @Description: 分页工具类
 * @Auther: 周明军
 * @Date: 2018/10/9 11:14
 */
@Data
public class PageUtil {
    //总记录数
    private int totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int pageNum;
    //列表数据
    private List<?> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param pageNum   当前页数
     */
    public PageUtil(List<?> list, int totalCount, int pageSize, int pageNum) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 分页
     */
    public PageUtil(Page<?> page) {
        this.list = page.getRecords();
        this.totalCount = page.getTotal();
        this.pageSize = page.getSize();
        this.pageNum = page.getCurrent();
        this.totalPage = page.getPages();
    }
}