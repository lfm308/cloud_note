package org.note.controller.user;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.note.entity.NoteResult;
import org.note.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class RegistController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/regist.do")
	@ResponseBody//ת��Ϊjson������
	public NoteResult execute(String name,String pwd,String nickname) throws Exception{
		//����service�е�ʵ���߼������ؽ��
		NoteResult result=userService.regist(name, pwd, nickname);
		
		return result;
		
	}
}
