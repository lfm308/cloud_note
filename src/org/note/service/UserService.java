package org.note.service;

import java.security.NoSuchAlgorithmException;

import org.note.entity.NoteResult;
//ÒµÎñÂß¼­
public interface UserService {

	public NoteResult checkLogin(String name,String pwd) throws Exception;
	
	public NoteResult regist(String name,String pwd,String nickname) throws Exception;
	
}
