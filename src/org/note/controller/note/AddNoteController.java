package org.note.controller.note;

import javax.annotation.Resource;

import org.note.entity.NoteResult;
import org.note.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class AddNoteController {
	
	@Resource
	private NoteService noteService;
	
	/**
	 * ´´½¨±Ê¼Ç
	 * @param userId
	 * @param bookId
	 * @param noteTitle
	 * @return
	 */
	@RequestMapping("/addnote.do")
	@ResponseBody
	public NoteResult execute(String userId,String bookId,String noteTitle){
		
		NoteResult result=noteService.addNote(userId,bookId,noteTitle);
		
		return result;
		
	}
	


}
