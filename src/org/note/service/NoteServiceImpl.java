package org.note.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.note.dao.NoteDao;
import org.note.dao.NoteShareDao;
import org.note.entity.Note;
import org.note.entity.NoteResult;
import org.note.entity.Share;
import org.note.util.NoteUtil;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao noteDao;
	@Resource
	private NoteShareDao shareDao;

	@Override
	/**
	 * 加载笔记
	 */
	public NoteResult loadNotes(String bookId) {
		NoteResult result = new NoteResult();

		List<Map> list = noteDao.findByBookId(bookId);
		result.setStatus(0);
		result.setMsg("查询笔记成功");
		result.setData(list);

		return result;
	}

	/**
	 * 添加笔记
	 */
	public NoteResult addNote(String userId, String bookId, String noteTitle) {

		Note note = new Note();

		note.setCn_notebook_id(bookId);
		note.setCn_note_title(noteTitle);
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("1");
		note.setCn_note_type_id("1");
		note.setCn_user_id(userId);
		note.setCn_note_body("");
		Long createTime = System.currentTimeMillis();
		note.setCn_note_create_time(createTime);

		noteDao.save(note);
		NoteResult result = new NoteResult();

		result.setStatus(0);
		result.setMsg("添加笔记成功");
		result.setData(noteId);

		return result;
	}

	/**
	 * 通过id获取笔记title和body
	 */
	public NoteResult showNote(String noteId) {
		Map map = noteDao.findByNoteId(noteId);
		NoteResult result = new NoteResult();

		result.setStatus(0);
		result.setMsg("获取笔记内容成功");
		result.setData(map);

		return result;
	}

	/**
	 * 编辑笔记
	 */
	@Override
	public NoteResult editNote(String noteId,String noteTitle,String noteBody) {
		Note note=noteDao.findNoteById(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		//如果笔记分享，更改分享笔记内容
		Share has_share=shareDao.findByNoteId(noteId);
		if(has_share!=null){//笔记已经分享
			
			has_share.setCn_share_title(noteTitle);
			has_share.setCn_share_body(noteBody);
			shareDao.editShare(has_share);
		}
		noteDao.editNoteById(note);
		
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("编辑成功");
		
		return result;
	}

	/**
	 * 更改笔记状态
	 */
	@Override
	public NoteResult updateStatus(String noteId) {
		NoteResult result=new NoteResult();
		
		noteDao.updateStatus(noteId);
		result.setStatus(0);
		result.setMsg("更改状态成功");
		
		return result;
	}

}
