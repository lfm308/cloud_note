package org.note.controller.note;

import javax.annotation.Resource;

import org.note.entity.NoteResult;
import org.note.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class UpdateNoteStatusController {
	
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/updatestatus.do")
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult result=noteService.updateStatus(noteId);
		
		return result;
	}

}
