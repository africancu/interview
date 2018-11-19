package cn.xzt.interview.common.utils;

import lombok.Data;

/**
 * Created by Jay on 2018/11/19 17:11
 */
@Data
public class RList extends R {
    private Long count;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int size;

}
