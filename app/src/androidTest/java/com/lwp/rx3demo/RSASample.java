package com.lwp.rx3demo;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSASample {
    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 512;
    //公钥
    private static final String PUBLIC_KEY = "PSP-PublicKey";
    //私钥
    private static final String PRIVATE_KEY = "PSP-PrivateKey";

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * Base64转码字符
     *
     * @param key 密钥值
     * @return byte[] 密钥转码值
     */
    public static byte[] getKeyByString(String key) throws Exception {
        return Base64.decodeBase64(key);
    }

    public static void main(String[] args) throws Exception {

        byte[] publicKey = Base64.decodeBase64("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAM4aQXyORsxok33h8e9QqUDRn7c4nOkIEcwgIjW7TovKg1fOoXAmnHfW06NtGbSbF2L4kbwLQnbsPLtJhlPAOWkCAwEAAQ==");
        byte[] privateKey = Base64
                .decodeBase64("");
//        System.out.println(PUBLIC_KEY + ": \n" + publicKey);
//        System.out.println(PRIVATE_KEY + ": \n" + privateKey);​
        String str = "Crld13579";
        System.out.println("密码原文:" + str);
//        //加密验证
        byte[] code2 = RSASample.encryptByPublicKey(str.getBytes(), publicKey);
        System.out.println("===========业务系统使用公钥对数据进行加密==============");
        System.out.println("加密后的数据：" + Base64.encodeBase64String(code2));
//        System.out.println("=============业务系统将数据传送给PSP======================");
//        System.out.println("===========PSP使用私钥对数据进行解密==============");
//        //解密验证
//        byte[] decode2 = RSASample.decryptByPrivateKey(code2, privateKey);
//        System.out.println("PSP解密后的数据：" + new String(decode2));
//        try {
//            // 密文还原
//            System.out.println("===========PSP使用私钥对密文还原==============");
//            byte[] code3 = RSASample.getKeyByString("IJAshf0Vq0T0pn6wjwqhpLiTcCDeprXY8vUgy3iY1VrPQNIG6z2colzr7Px77fSviLdpPrI9KuU91U6+ywA8OA==");
//            byte[] decode3 = RSASample.decryptByPrivateKey(code3, privateKey);
//            System.out.println("PSP解密后的数据：" + new String(decode3));
//        } catch (Exception e) {
//            System.out.println("解密失败");
//        }
    }

}
