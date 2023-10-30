package learning.spring.service;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrypter {
    public  static String encrypt( String unhashedpassword) throws NoSuchAlgorithmException
    {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        md5.update(unhashedpassword.getBytes());
        byte[]digested= md5.digest();
        return DatatypeConverter.printHexBinary(digested);

    }
}
