package org.note.controller.note;

import javax.annotation.Resource;

import org.note.entity.NoteResult;
import org.note.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 更新笔记内容
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/note")
public class UpdateNoteController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/shownote.do")
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult result=noteService.showNote(noteId);
		
		return result;
		
	}

}
