package cn.xzt.interview.common.utils;

import cn.xzt.interview.common.constant.ResultStatus;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Jay on 2018/10/24 15:01.
 * 参数检测工具类
 *
 * @author Jay
 */
public class ParamCheckUtil {
    public static String checkJsonKey(JSONObject jsonObject, String... key) {
        for (String k : key) {
            if (!jsonObject.containsKey(k)) {
                return k;
            }
        }
        return null;
    }

    public static R checkPrams(String params, String... keys) {
        R response = new R();
        if (StringUtil.isBlank(params)) {
            response.setCode(ResultStatus.PARAM_EMPTY.getCode());
            response.setMessage(ResultStatus.PARAM_EMPTY.getMessage());
            return response;
        }

        try {
            JSONObject jsonObject = JSONObject.parseObject(params);

            if (keys != null) {
                String checkJsonKey = checkJsonKey(jsonObject, keys);

                if (!StringUtil.isBlank(checkJsonKey)) {
                    response.setCode(ResultStatus.PARAM_INSUFFICIENT.getCode());
                    response.setMessage(ResultStatus.PARAM_INSUFFICIENT.getMessage() + "，缺少参数：" + checkJsonKey);
                    return response;
                }
            }

        } catch (JSONException e) {
            response.setCode(ResultStatus.INVALID_JSON.getCode());
            response.setMessage(ResultStatus.INVALID_JSON.getMessage());
            return response;
        }

        return null;
    }
}
