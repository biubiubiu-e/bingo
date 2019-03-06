package com.xfh.bingo.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/27 19:23
 */

public class AESUtils {
    private static final String AES = "AES";

    /**
     * 加密
     *
     * @return
     */
    public static byte[] encrypt(byte[] src, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);
        //设置密钥和加密形式
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        return cipher.doFinal(src);
    }

    /**
     * 解密
     *
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String key)  throws Exception  {
        Cipher cipher = Cipher.getInstance(AES);
        //设置加密Key
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);
        //设置密钥和解密形式
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        return cipher.doFinal(src);
    }

    /**
     * 二行制转十六进制字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0){
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    /**
     * 解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public final static String decrypt(String data, String key) {
        try {
            return new String(decrypt(hex2byte(data.getBytes()),
                    key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String encrypt(String data, String CRYPT_KEY) {
        try {
            return byte2hex(encrypt(data.getBytes(), CRYPT_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
