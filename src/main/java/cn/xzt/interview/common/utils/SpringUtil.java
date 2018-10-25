package cn.xzt.interview.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Auther: 周明军
 * @Date: 2018/9/21 17:52
 * @Description:
 */
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        System.out.println("---------->" + applicationContext);
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }


//    private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
//    private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
//
//    /**
//     * 获取当前请求对象
//     *
//     * @return
//     */
//    public static HttpServletRequest getRequest() {
//        try {
//
//            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /**
//     * 通过spring的webapplicationcontext上下文对象读取bean对象
//     *
//     * @param sc       上下文servletConext对象
//     * @param beanName 要读取的bean的名称
//     * @return 返回获取到的对象。获取不到返回null
//     */
//    public static Object getBean(ServletContext sc, String beanName) {
//        return WebApplicationContextUtils.getWebApplicationContext(sc).getBean(beanName);
//    }
//
//    /**
//     * 通过spring的webapplicationcontext上下文对象读取bean对象
//     *
//     * @param beanName 要读取的bean的名称
//     * @return 返回获取到的对象。获取不到返回null
//     */
//    public static Object getBean(String beanName) {
//        return WebApplicationContextUtils.getWebApplicationContext(SpringUtil.getRequest().getServletContext())
//                .getBean(beanName);
//    }
//
//    /**
//     * 通过spring的webapplicationcontext上下文对象读取bean对象
//     */
//    public static <T> T getBean(Class<T> cls) {
//        HttpServletRequest request = SpringUtil.getRequest();
//        ServletContext servletContext = request.getServletContext();
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        T bean = webApplicationContext.getBean(cls);
//        return bean;
//    }
//
//    public static HttpServletResponse getResponse() {
//        return (HttpServletResponse) responseLocal.get();
//    }
//
//    public static void setResponse(HttpServletResponse response) {
//        responseLocal.set(response);
//    }
//
//    public static void setResquest(HttpServletRequest request) {
//        requestLocal.set(request);
//    }
//
//    public static HttpSession getSession() {
//        return SpringUtil.getRequest().getSession();
//    }


}