package cn.xzt.interview.common.utils;

import java.security.MessageDigest;

/**
 * @Description: 加密工具
 * @Auther: 周明军
 * @Date: 2018/9/30 09:58
 */
public class EncryptUtil {

    /**
     * 生成32位MD5加密
     * @param s
     * @return
     */
    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5("12345"));
        System.out.println("827CCB0EEA8A706C4C34A16891F84E7B".length());
    }

}