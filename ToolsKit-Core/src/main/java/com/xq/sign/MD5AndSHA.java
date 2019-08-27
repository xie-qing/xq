package com.xq.sign;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/8/14 16:47
 */
public class MD5AndSHA {

    private static final String KEY_MD5 = "MD5";


    private static final String KEY_SHA = "SHA";

    private static final String EN_CODING = "UTF-8";

    /**
     * MAC算法可选以下多种算法
     *
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";

    /**
     * 〈〉
     * 功能描述: 解密<br>
     * 〈/〉
     * @param data 要解密的密码
     * @return 解密后的密码
     * @author XQ
     * @date 2019/8/14 16:53
     */
    public static String decryptBase64(String data){
        return  new String(Base64.getDecoder().decode(data.getBytes()));
    }

    /**
     * 〈〉
     * 功能描述: 加密 <br>
     * 〈/〉
     * @param data 加密的密码
     * @return 加密后的密码
     * @author XQ
     * @date 2019/8/14 17:01
     */
    public static String encryptBase64(String data) throws UnsupportedEncodingException {
        return  Base64.getEncoder().encodeToString(data.getBytes(EN_CODING));
    }

    /**
     * 〈〉
     * 功能描述: MD5加密<br>
     * 〈/〉
     * @param data 数据
     * @return String
     * @author XQ
     * @date 2019/8/14 17:15
     */
    public static byte[] encryptMd5(String data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data.getBytes());

        return md5.digest();
    }
    
    public static void main(String[] args) throws Exception {
        String data = "你还";
        String encryptBase64 = encryptBase64(data);
        System.out.println("BASE64加密后的密码：" + encryptBase64);
        String decryptBase64 = decryptBase64(encryptBase64);
        System.out.println("BASE64解密后的密码：" + decryptBase64);

        BigInteger mD5 = new BigInteger(encryptMd5(data));
        System.out.println("MD5解密后的密码：" + mD5);

    }
}
