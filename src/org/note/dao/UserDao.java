package org.note.dao;

import org.note.entity.User;
//���ݷ��ʲ�
public interface UserDao {
	/**
	 * ͨ�����ֻ�ȡuser
	 * @param name
	 * @return
	 */
	public User findByName(String name);
	
	/**
	 * ����user��Ϣ
	 * @param user
	 */
	public void save(User user);
	

}
