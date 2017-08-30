package org.note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.note.dao.NoteBookDao;
import org.note.entity.NoteBook;
import org.note.entity.NoteResult;
import org.note.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class NoteBookServiceImpl implements NoteBookService{
/**
 * 通过用户id查询所有的笔记本
 */
	@Resource
	private NoteBookDao bookDao;
	public NoteResult loadBooks(String userId) {
		List<NoteBook> list=bookDao.findByUser(userId);
		NoteResult result=new NoteResult();
		
		result.setStatus(0);
		result.setMsg("查询笔记本成功");
		result.setData(list);
		return result;
	}
	/**
	 * 创建笔记本
	 */
	@Override
	public NoteResult addBook(String bookName,String userId) {
		NoteResult result=new NoteResult();
		
		//创建笔记本
		NoteBook book=new NoteBook();
		book.setCn_notebook_name(bookName);
		book.setCn_user_id(userId);
		book.setCn_notebook_type_id("5");
		String noteId=NoteUtil.createId();
		book.setCn_notebook_id(noteId);
		Timestamp createTime=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(createTime);
		
		result.setStatus(0);
		result.setMsg("创建成功");
		
		result.setData(noteId);//返回noteId为了后面显示在book列表
		
		bookDao.save(book);
		
		
		return result;
	}

}
