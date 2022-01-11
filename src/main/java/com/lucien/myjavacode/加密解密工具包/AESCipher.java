/**
 * zoneland.net Inc.
 * Copyright (c) 2002-2017 All Rights Reserved.
 */
package com.lucien.myjavacode.加密解密工具包;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


/**
 * 
 * @author qijian
 * @version $Id: AESCipher.java, v 0.1 2017-12-28 上午11:13:59 qijian Exp $
 */

public class AESCipher {
	
	private static final String IV_STRING = "A-16-Byte-String";
	private static final String charset = "UTF-8";
	
	public static String aesEncryptString(String content, String key) {
		try {
			byte[] contentBytes = content.getBytes(charset);
			byte[] keyBytes = key.getBytes(charset);
			byte[] encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);
			return Base64.encode(encryptedBytes);
		} catch (Exception e) {
			System.out.println(content+"内容加密出错："+e.getMessage());
		}
		return "";
	}

	public static String aesDecryptString(String content, String key){
		//Decoder decoder = Base64.getDecoder();
	    try {
			byte[] encryptedBytes = Base64.decode(content);
			byte[] keyBytes = key.getBytes(charset);
			byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
			return new String(decryptedBytes, charset);
		} catch (Exception e) {
			System.out.println(content+"内容解密出错："+e.getMessage());
		}
	    return "";
	}
	
	public static byte[] aesEncryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	    return cipherOperation(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
	}
	
	public static byte[] aesDecryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	    return cipherOperation(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
	}
	
	private static byte[] cipherOperation(byte[] contentBytes, byte[] keyBytes, int mode) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
		
	    byte[] initParam = IV_STRING.getBytes(charset);
	    IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(mode, secretKey, ivParameterSpec);

 	 	return cipher.doFinal(contentBytes);
	}
	
	public static void main(String[] args) {
		String authKey = "hljtest2#"+"1516370632994";
		System.out.println(authKey);
		String aes = AESCipher.aesEncryptString(authKey, "teloa.bf.ctc.com");
		System.out.println(aes);
		String abc = "80QEnlhve2zqkYACd35M7K811Sss8faZ+gkP/PLVuaQ=";
		String key = AESCipher.aesDecryptString(abc, "teloa.bf.ctc.com");
		System.out.println(key);
		if(authKey.indexOf("#")>-1){
			String time = authKey.split("#")[1];
			long start = 0;
			try {
				start = Long.valueOf(time);
			} catch (Exception e) {
			}
			System.out.println(start);
		}

		String contenttest = AESCipher.aesDecryptString("RklWTTQnHExHsgUxo165bRSOwpVvWd6//FWHx21Jz1he5B3Ufe4TU6Q2ia+1OVJr2o4n8FbVc9Ws4Mhc0TqdPrI79bf8O+vo4Irlir4H9AQ=", "16BytesLengthKey");
		System.out.println(contenttest);

		System.out.println(UUID.randomUUID().toString().toUpperCase().replaceAll("-", "").substring(16));
	}

}
