package cn.org.bjca.ec.platform.utils;

/**
 * @Author: zwt
 * @Date: Create in 11:00 2019-10-25
 */
public class NumberStringUtil {

    /**
     * 左补零
     *
     * @param str
     * @param length
     * @return
     */
    public static String addLeftZero(String str, int length) {
        int str_length = str.length();
        for (int i = 0; i < (length - str_length); i = i + 1) {
            str = '0' + str;
        }
        return str;
    }

    /**
     * 右补零
     *
     * @param str
     * @param length
     * @return
     */
    public static String addRightZero(String str, int length) {
        int str_length = str.length();
        for (int i = 0; i < (length - str_length); i = i + 1) {
            str = str + '0';
        }
        return str;
    }

    /**
     * 右补字符
     *
     * @param str
     * @param length
     * @param lpad
     * @return
     */
    public static String addRightChar(String str, int length, String lpad) {
        if (str != null) {
            int str_length = str.length();
            for (int i = 0; i < (length - str_length); i = i + 1) {
                str = str + lpad;
            }
        }
        return str;
    }

    /**
     * 左补字符
     *
     * @param str
     * @param length
     * @param lpad
     * @return
     */
    public static String addLeftChar(String str, int length, String lpad) {
        if (str != null) {
            int str_length = str.length();
            for (int i = 0; i < (length - str_length); i = i + 1) {
                str = lpad + str;
            }
        }
        return str;
    }
}
