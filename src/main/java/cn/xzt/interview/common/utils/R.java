package cn.xzt.interview.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 周明军
 * @Date: 2018/9/20 09:12
 * @Description: 响应体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 具体内容
     */
    private Object result;

}