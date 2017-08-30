package org.note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase;

public class NoteUtil {
	/**
	 * ��uuid����idֵ�������ظ�
	 * @return
	 */
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	//md5���ȳ���ȳ�
	public static String md5(String msg) throws NoSuchAlgorithmException{
		//����md5��msg����
		MessageDigest md=MessageDigest.getInstance("MD5");
		byte[] input=msg.getBytes();
		byte[] output=md.digest(input);//���ֽ���Ϣ����
		
		//��md5����Ľ��ת���ַ���
		String result=Base64.encodeBase64String(output);
		return result;
		
	}
	
//	public static void main(String[] args) throws NoSuchAlgorithmException {
//		System.out.println(NoteUtil.md5("1234"));
//	}

}
