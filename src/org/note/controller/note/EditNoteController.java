package org.note.controller.note;

import javax.annotation.Resource;

import org.note.entity.NoteResult;
import org.note.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/note")
public class EditNoteController {
	
	@Resource
	private NoteService noteService;
	/**
	 * ±à¼­±Ê¼ÇÄÚÈÝ
	 * @param noteId
	 * @param noteTitle
	 * @param noteBody
	 * @return
	 */
	@RequestMapping("/editnote.do")
	@ResponseBody
	public NoteResult execute(String noteId,String noteTitle,String noteBody){
		
		NoteResult  result=noteService.editNote(noteId, noteTitle, noteBody);
		
		return result;
	}

}
