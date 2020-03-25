//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cmazxiaoma.lotteryTest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    private AESUtils() {
    }

    public static void main(String[] args) {
        String content = "13_null_123810178";
        String password = "a1b2c3d4e5";
        System.out.println("加密前：" + content);
        String s = encrypt(content, password);
        System.out.println("加密后：" + s);
        String s1 = decrypt(s, password);
        System.out.println("解密后：" + s1);
    }

    public static String encrypt(String bef_aes, String password) {
        byte[] byteContent = null;

        try {
            byteContent = bef_aes.getBytes("utf-8");
        } catch (UnsupportedEncodingException var4) {

        }

        return encrypt(byteContent, password);
    }

    public static String encrypt(byte[] content, String password) {
        try {
            SecretKey secretKey = getKey(password);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, key);
            byte[] result = cipher.doFinal(content);
            String aft_aes = parseByte2HexStr(result);
            return aft_aes;
        } catch (NoSuchAlgorithmException var8) {

        } catch (NoSuchPaddingException var9) {


        } catch (InvalidKeyException var10) {

        } catch (IllegalBlockSizeException var11) {

        } catch (BadPaddingException var12) {

        }

        return null;
    }

    public static String decrypt(String aft_aes, String password) {
        try {
            byte[] content = parseHexStr2Byte(aft_aes);
            SecretKey secretKey = getKey(password);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            byte[] result = cipher.doFinal(content);
            String bef_aes = new String(result);
            return bef_aes;
        } catch (Exception var9) {
            return null;
        }
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) throws NumberFormatException {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for(int i = 0; i < hexStr.length() / 2; ++i) {
                int value = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16);
                result[i] = (byte)value;
            }

            return result;
        }
    }

    public static SecretKey getKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            _generator.init(128, secureRandom);
            return _generator.generateKey();
        } catch (Exception var3) {
            throw new RuntimeException("初始化密钥出现异常", var3);
        }
    }
}
