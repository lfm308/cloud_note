package org.note.controller.user;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.note.entity.NoteResult;
import org.note.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
/**
 * ��¼���Ʋ�
 * @author Administrator
 *
 */
@Controller//ɨ��controller�������
@RequestMapping("/user")
public class LoginController {
	
	@Resource
	private UserServiceImpl userServiecImpl;//ע��
	//http://localhost:8080/cloudnote/user/login.do?name=demo&pwd=1234
	@RequestMapping("/login.do")
	@ResponseBody//��NoteResultת��json���
	public NoteResult execute(String name,String pwd) throws Exception{
		
		return userServiecImpl.checkLogin(name, pwd);
		
	}
}
