package com.lwp.rx3demo;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author xiaochengpeng
 * @date 2019/9/3 10:41
 */
public class Sha256Util {

    public static String sha256(final String contents)
    {
        String encryptedContents = "";
        try
        {
            MessageDigest messageDigest = null;
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(contents.getBytes(StandardCharsets.UTF_8));
            encryptedContents = byteToString(messageDigest.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

        return encryptedContents;
    }

    private static String byteToString(byte[] bByte)
    {
        StringBuffer encrytStrBuf = new StringBuffer();

        for (int i = 0; i < bByte.length; i++)
        {
            if (Integer.toHexString(0xFF & bByte[i]).length() == 1)
                encrytStrBuf.append("0").append(Integer.toHexString(0xFF & bByte[i]));
            else
            {
                encrytStrBuf.append(Integer.toHexString(0xFF & bByte[i]));
            }
        }
        return encrytStrBuf.toString().toUpperCase();
    }

    private static String toXML(Map<String , String> map){
        StringBuffer sb = new StringBuffer() ;
        String login = map.get("LOGIN");
        for(Map.Entry<String , String> entry : map.entrySet()){
            String value = (String)entry.getValue() ;
            if("PASSWD".equals(entry.getKey())){
                value = Sha256Util.sha256(value);
                value = Sha256Util.sha256("CRCLDAP+" + login + "+" + value);
            }
            sb.append("<"+(String)entry.getKey()+">").append(value).append("</"+(String)entry.getKey()+">") ;
        }
        return sb.toString() ;
    }

    /**加密方式需要sha256加密俩次 demo如下*/
    public static void main(String[] args){
        String password = "Crld13579";
        String username = "LIYIHE";

        String passwd = Sha256Util.sha256(password);
        passwd = Sha256Util.sha256("CRCLDAP+" + username + "+" + passwd);
        System.out.println("外部用户加密后的密码:"+ passwd);

        //外部用户加密
        String exitPasswd =Sha256Util.sha256(Md5Util.md5Password(password).toUpperCase());
        System.out.println("内部用户加密后的密码:"+ exitPasswd);
    }
}
