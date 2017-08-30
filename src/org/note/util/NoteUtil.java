package org.note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase;

public class NoteUtil {
	/**
	 * 用uuid生成id值，不会重复
	 * @return
	 */
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	//md5不等长变等长
	public static String md5(String msg) throws NoSuchAlgorithmException{
		//利用md5对msg处理
		MessageDigest md=MessageDigest.getInstance("MD5");
		byte[] input=msg.getBytes();
		byte[] output=md.digest(input);//将字节信息处理
		
		//将md5处理的结果转成字符串
		String result=Base64.encodeBase64String(output);
		return result;
		
	}
	
//	public static void main(String[] args) throws NoSuchAlgorithmException {
//		System.out.println(NoteUtil.md5("1234"));
//	}

}
