package org.note.controller.notebook;


import java.util.List;

import javax.annotation.Resource;

import org.note.entity.NoteBook;
import org.note.entity.NoteResult;
import org.note.service.NoteBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notebook")
public class LoadBooksController {
	@Resource
	private NoteBookService bookService;
	
	/**
	 * 通过userId获取结果
	 * @param userId
	 * @return
	 */
	@RequestMapping("/loadbooks.do")
	@ResponseBody
	public NoteResult execute(String userId){
		NoteResult result=bookService.loadBooks(userId);
		return result;
		
	}

}
