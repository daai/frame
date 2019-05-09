package zwt.example.core;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author: zwt
 * @Date: Create in 11:00 2019-04-26
 */
public class MsgUtil {
    /**
     * 参数排序
     * @param params
     * @return
     */
    public static Map signForInspiry(Map params) {
        Map<Object, Object> treeMap = new TreeMap<>();
        Set es = params.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v)) {
                treeMap.put(k, v);
            }
        }
        return treeMap;
    }

    /**
     * 参数拼接
     * @param map
     * @return
     */
    public static String packRsaSignSrc(Map map) {
        StringBuffer buffer = new StringBuffer();
        String equal = "=";
        String seq = "&";
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);
            if (null == value || key.isEmpty()) {
                continue;
            }
            if (value instanceof Map) {
                value = JSON.toJSON(value);
            }
            if (value instanceof List) {
                value = JSON.toJSON(value);
            }
            buffer.append(key).append(equal).append(value).append(seq);
        }
        String result = null;
        if (buffer.length() > 0) {
            result = buffer.toString().substring(0, buffer.length() - 1);
        }
        return result;
    }

    /**
     * 参数URLEncoder编码，用来http get请求不能传输中文参数问题
     * @param map
     * @return
     */
    public static String packRsaSignSrcEncode(Map map) {
        StringBuffer buffer = new StringBuffer();
        String equal = "=";
        String seq = "&";
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);
            if (null == value || key.isEmpty()) {
                continue;
            }
            if (value instanceof Map) {
                value = JSON.toJSON(value);
            }
            if (value instanceof List) {
                value = JSON.toJSON(value);
            }
            try {
                key = URLEncoder.encode(key, "utf-8");
                value = URLEncoder.encode(value.toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            buffer.append(key).append(equal).append(value).append(seq);
        }
        String result = null;
        if (buffer.length() > 0) {
            result = buffer.toString().substring(0, buffer.length() - 1);
        }
        return result;
    }

}
