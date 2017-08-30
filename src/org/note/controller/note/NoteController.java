package org.note.controller.note;

import javax.annotation.Resource;

import org.note.entity.Note;
import org.note.entity.NoteResult;
import org.note.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource
	private NoteService noteService;
	
	/**
	 * 加载笔记,显示列表
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult execute(String bookId){
		
		NoteResult result=noteService.loadNotes(bookId);
		
		return result;
	}
		

}
