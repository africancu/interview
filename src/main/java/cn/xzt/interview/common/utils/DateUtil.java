package cn.xzt.interview.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: 周明军
 * @Date: 2018/9/25 14:34
 * @Description: 时间工具类
 */
public class DateUtil {

    private static final String EN_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    private static final String ZH_YMD_HMS = "yyyy年MM月dd日 HH时mm分ss秒";
    private static final DateFormat EN_FORMAT = new SimpleDateFormat(EN_YMD_HMS);
    private static final DateFormat ZH_FORMAT = new SimpleDateFormat(ZH_YMD_HMS);

    /**
     * 时间戳转日期
     * @param millis
     * @return
     */
    public static Date timestampToDateTime(Long millis){
        return new Date(millis);
    }

    /**
     * 日期转时间戳
     * @param date
     * @return
     */
    public static long datetimeToTimestamp(Date date){
        return date.getTime();
    }

    /**
     * 获取英文标准格式 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String standardFormat(Date date){
        return EN_FORMAT.format(date);
    }

    /**
     * 获取中文格式 yyyy年MM月dd日 HH时mm分ss秒
     * @param date
     * @return
     */
    public static String localFormat(Date date){
        return ZH_FORMAT.format(date);
    }

}