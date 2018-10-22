package cn.xzt.interview.common.utils;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: 周明军
 * @Date: 2018/9/21 17:52
 * @Description:
 */
public class SpringUtil {

    /**
     * 通过spring的webapplicationcontext上下文对象读取bean对象
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return WebApplicationContextUtils.getWebApplicationContext(SpringUtil.getRequest().getServletContext()).getBean(beanName);
    }

    private static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }


}