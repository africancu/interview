package cn.xzt.interview.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 周明军
 * @Date: 2018/9/20 17:14
 * @Description: JSON工具类
 */
public class JSONUtil {

    /**
     * 校验JSON格式
     * @param input
     * @return
     */
    public static boolean validateJSON(String input){
        try {
            JSONObject.parse(input);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * JSON字符串转Java对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T jSONToObject(String jsonStr, Class<T> clazz) {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        T t = JSONObject.toJavaObject(jsonObject, clazz);
        return t;
    }

    /**
     * JSON字符串转Java对象集合
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> JSONToList(String jsonStr, Class<T> clazz) {
        return JSONArray.parseArray(jsonStr, clazz);
    }

    /**
     * JSON字符串转Map
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> JSONToMap(String jsonStr) {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Iterator<String> keyIter = jsonObject.keySet().iterator();
        String key;
        Object value;
        Map<String, Object> valueMap = new HashMap<String, Object>();

        while (keyIter.hasNext()) {
            key = (String) keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }
        return valueMap;
    }

    /**
     * 对象, 集合，Map转JSON字符串
     *
     * @param object
     * @return
     */
    public static String objectToJSON(Object object) {
        return JSON.toJSONString(object);
    }

}