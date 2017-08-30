package org.note.service;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.note.dao.UserDao;
import org.note.entity.NoteResult;
import org.note.entity.User;
import org.note.util.NoteUtil;
import org.springframework.stereotype.Service;

@Service//ɨ��service���
public class UserServiceImpl  implements UserService{
	@Resource
	private UserDao userDao;//ע��

	/**
	 * ����û�������
	 * @throws NoSuchAlgorithmException 
	 */
	
	public NoteResult checkLogin(String name, String password) throws Exception {
		NoteResult result =new NoteResult();
		User user=userDao.findByName(name);
		if(user==null){
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
		//���û�������������
		String md5_pwd=NoteUtil.md5(password);
		
		if(!user.getCn_user_password().equals(md5_pwd)){
			result.setStatus(2);
			result.setMsg("���벻��ȷ");
			return result;
		}
		
		result.setStatus(0);
		result.setMsg("�û���������ȷ");
		result.setData(user.getCn_user_id());//�����û�id
		return result;
	}
/**
 * �û�ע�Ṧ�ܵ�ʵ�֣�ע��ɹ��������ݿ�
 */
	public NoteResult regist(String name, String pwd, String nickname) throws Exception {
		NoteResult result=new NoteResult();
		
		//����û����Ƿ��Ѵ���
		User has_user=userDao.findByName(name);
		
		if(has_user!=null){
			result.setStatus(1);
			result.setMsg("�û����ѱ�ռ��");
			return result;
		}
		
		User user=new User();
		
		user.setCn_user_name(name);
		
		String md5_pwd=NoteUtil.md5(pwd);
		user.setCn_user_password(md5_pwd);
		
		user.setCn_user_desc(nickname);
		
		String userId=NoteUtil.createId();
		user.setCn_user_id(userId);
		//����userDao����
		userDao.save(user);
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		
		return result;
	}

}
