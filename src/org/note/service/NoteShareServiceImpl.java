package org.note.service;

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
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class NoteShareServiceImpl implements NoteShareService{

	@Resource
	private NoteShareDao shareDao;
	@Resource
	private NoteDao noteDao;
	/**
	 * 分享笔记
	 */
	public NoteResult shareDao(String noteId) {
		NoteResult result =new NoteResult();
		Share has_share=shareDao.findByNoteId(noteId);
		if(has_share!=null){
			result.setStatus(1);
			result.setMsg("该笔记已被分享");
			return result;
		}
		
		Map map=noteDao.findByNoteId(noteId);
		String noteTitle=(String) map.get("cn_note_title");
		String noteBody=(String) map.get("cn_note_body");
		
		Share share=new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_title(noteTitle);
		share.setCn_share_body(noteBody);
		String shareId=NoteUtil.createId();
		share.setCn_share_id(shareId);
		
		shareDao.insertIntoShare(share);
		
		
		result.setStatus(0);
		result.setMsg("分享笔记成功");
		
		return result;
	}
	/**
	 * 搜索已分享的笔记
	 */
	public NoteResult searchShareNotes(String key) {
		if(key!=null && !"".equals(key)){
			key="%"+key+"%";
		}else{
			key="%";
		}
		
		List<Share> list=shareDao.findSharesByKey(key);
		
		NoteResult result=new NoteResult();
		
		result.setStatus(0);
		result.setMsg("搜索成功");
		result.setData(list);
		
		return result;
	}
	
}
