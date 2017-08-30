package org.note.dao;

import org.note.entity.User;
//数据访问层
public interface UserDao {
	/**
	 * 通过名字获取user
	 * @param name
	 * @return
	 */
	public User findByName(String name);
	
	/**
	 * 插入user信息
	 * @param user
	 */
	public void save(User user);
	

}
