package org.note.service;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.note.dao.UserDao;
import org.note.entity.NoteResult;
import org.note.entity.User;
import org.note.util.NoteUtil;
import org.springframework.stereotype.Service;

@Service//扫描service组件
public class UserServiceImpl  implements UserService{
	@Resource
	private UserDao userDao;//注入

	/**
	 * 检测用户名密码
	 * @throws NoSuchAlgorithmException 
	 */
	
	public NoteResult checkLogin(String name, String password) throws Exception {
		NoteResult result =new NoteResult();
		User user=userDao.findByName(name);
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//将用户输入的密码加密
		String md5_pwd=NoteUtil.md5(password);
		
		if(!user.getCn_user_password().equals(md5_pwd)){
			result.setStatus(2);
			result.setMsg("密码不正确");
			return result;
		}
		
		result.setStatus(0);
		result.setMsg("用户名密码正确");
		result.setData(user.getCn_user_id());//存入用户id
		return result;
	}
/**
 * 用户注册功能的实现，注册成功存入数据库
 */
	public NoteResult regist(String name, String pwd, String nickname) throws Exception {
		NoteResult result=new NoteResult();
		
		//检测用户名是否已存在
		User has_user=userDao.findByName(name);
		
		if(has_user!=null){
			result.setStatus(1);
			result.setMsg("用户名已被占用");
			return result;
		}
		
		User user=new User();
		
		user.setCn_user_name(name);
		
		String md5_pwd=NoteUtil.md5(pwd);
		user.setCn_user_password(md5_pwd);
		
		user.setCn_user_desc(nickname);
		
		String userId=NoteUtil.createId();
		user.setCn_user_id(userId);
		//调用userDao保存
		userDao.save(user);
		result.setStatus(0);
		result.setMsg("注册成功");
		
		return result;
	}

}
