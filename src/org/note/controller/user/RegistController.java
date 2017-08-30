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
	@ResponseBody//转换为json结果输出
	public NoteResult execute(String name,String pwd,String nickname) throws Exception{
		//调用service中的实现逻辑，返回结果
		NoteResult result=userService.regist(name, pwd, nickname);
		
		return result;
		
	}
}
