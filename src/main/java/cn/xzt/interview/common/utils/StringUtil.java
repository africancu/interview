package cn.xzt.interview.common.utils;

/**
 * @Auther: 周明军
 * @Date: 2018/9/21 16:15
 * @Description: 字符串工具类
 */
public class StringUtil {

    /**
     * 判断空字符串
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return (str == null || str.trim().equals("") || str.length() < 1);
    }

}