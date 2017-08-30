package org.note.dao.test;

import org.note.dao.UserDao;
import org.note.entity.User;

public class TestUserDao extends TestBase {

	public static void main(String[] args) {
		UserDao dao=getContext().getBean("userDao",UserDao.class);
//		User user=dao.findByName("demo1");
		User user=new User();
		user.setCn_user_id("12");
		user.setCn_user_name("li");
		dao.save(user);
		System.out.println(dao.findByName("li").getCn_user_id());
		
	}
}
