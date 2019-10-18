package zwt.example.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.util.StringUtils;


import java.util.*;


public class StringUtil {
    public StringUtil() {
    }

    /**
     * print string
     * @param o
     * @return
     */
    public static String reflectionToString(Object o){
        if(o == null) return org.apache.commons.lang.StringUtils.EMPTY;
        StringBuilder toStr = new StringBuilder();
        if(o instanceof Collection){
            Iterator it = ((Collection) o).iterator();
            while (it.hasNext()){
                toStr.append(reflectionToString(it.next()));
                toStr.append("\n");
            }
        }else if(o instanceof Map){
            Iterator<Map.Entry> mit = ((Map) o).entrySet().iterator();
            while (mit.hasNext()){
                Map.Entry entry = mit.next();
                String kv = entry.getKey() + ": " + reflectionToString(entry.getValue());
                toStr.append(kv);
                toStr.append(" ");
            }
        } else if(o instanceof Object[]){
            for (Object temp: (Object[])o){
                toStr.append(reflectionToString(temp));
            }
        }else if(o instanceof String){
            toStr.append(o);
        } else {
            toStr.append(ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE));
        }
        return toStr.toString();
    }

    //public static Map<String, Object> stringToMap(String str) {
    //    Gson gson = new Gson();
    //    JsonObject jsonObject = (JsonObject)gson.fromJson(str, JsonObject.class);
    //    return jsonToMap(jsonObject);
    //}
    //
    //public static Map<String, Object> jsonToMap(JsonObject jsonObject) {
    //    Set it = jsonObject.entrySet();
    //    HashMap map = new HashMap();
    //    Iterator var4 = it.iterator();
    //
    //    while(var4.hasNext()) {
    //        Map.Entry entry = (Map.Entry)var4.next();
    //        String key = (String)entry.getKey();
    //        Object value = entry.getValue();
    //        if(value instanceof JsonArray) {
    //            map.put(key, value.toString().replace("\"", ""));
    //        } else if(value instanceof JsonObject) {
    //            map.put(key, value.toString().replace("\"", ""));
    //        } else {
    //            map.put(key, value.toString().replace("\"", ""));
    //        }
    //    }
    //
    //    return map;
    //}
    //
    //public static List<Object> jsonToList(JsonArray json) {
    //    ArrayList list = new ArrayList();
    //
    //    for(int i = 0; i < json.size(); ++i) {
    //        JsonElement value = json.get(i);
    //        if(value instanceof JsonArray) {
    //            list.add(jsonToList((JsonArray)value));
    //        } else if(value instanceof JsonObject) {
    //            list.add(jsonToMap((JsonObject)value));
    //        } else {
    //            list.add(value);
    //        }
    //    }
    //
    //    return list;
    //}

    public static String getSysTime() {
        long start = System.currentTimeMillis();
        return null;
    }

    public static boolean isNull(String key) {
        return key == null || key.equals("");
    }

    public static boolean isBlank(String str) {
        if (str == null || "".equals(str) || "null".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isNull(Object str) {
        if (str == null || "".equals(str.toString()) || "null".equals(str.toString())) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return true;
    }

    public static boolean isSameString(String str1, String str2) {
        if (str1 == null || "".equals(str1)) {
            if (str2 == null || "".equals(str2)) {
                return true;
            }
            return false;
        }
        if (str1 != null && str1.equals(str2)) {
            return true;
        }
        return false;
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return ((str1 == null) ? false : (str2 == null) ? true : str1.equalsIgnoreCase(str2));
    }

    public static String trimToEmpty(String str) {
        return str == null ? null : str.trim();
    }

    public static String[] getItems(String str){
        String[] items = null;
        if(!StringUtils.isEmpty(str)){
            items= str.split(",");
            if(items != null && items.length > 0){
                return items;
            }
        }
        return items;
    }

    //身份证出生年月截取
    public static String getBirthday(String str) {
        String birthdayStr = "";
        if(!StringUtils.isEmpty(str)){
            birthdayStr = str.trim();
            //判断15或 18位截取
            if(birthdayStr.length() == 15){
                birthdayStr = "19"+birthdayStr.substring(6,12);
            }else if(birthdayStr.length() == 18){
                birthdayStr = birthdayStr.substring(6,14);
            }
        }
        return birthdayStr;
    }



    public static String getString(Object object) {
        if (object == null) {
            return null;
        }
        return object.toString();
    }



    /**
     *
     * @param str
     * @return
     */
    public static boolean isBlankWithTrim(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static String cutOutStr(String str,int length){
        if(StringUtils.isEmpty(str)){
            return str;
        }
        if(str.length()>=length){
            return str.substring(0,length-1);
        }
        return str;
    }

    /**
     * @Description: 处理数据库插入长字段问题，如某字段数据库最大长度为3000，
     * 待插入字符长度为2000（超过3000的三分之一），则按最大长度的三分之一截取字符串并返回
     * @return
     */
    public static String handleFieldTooLong(String str, int max) {
        if (isBlank(str)) {
            return null;
        }
        if (str.length() > max / 3) {
            return str.substring(0, max / 3);
        } else {
            return str;
        }
    }
}
