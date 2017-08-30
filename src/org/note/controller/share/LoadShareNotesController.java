package org.note.controller.share;

import javax.annotation.Resource;

import org.note.entity.NoteResult;
import org.note.service.NoteShareService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/share")
public class LoadShareNotesController {
	@Resource
	private NoteShareService shareService;
	
	@RequestMapping("/loadshare.do")
	@ResponseBody
	public NoteResult execute(String key){
		NoteResult result=shareService.searchShareNotes(key);

		return result;
		
	}

}
