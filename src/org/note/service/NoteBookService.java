package org.note.service;

import org.note.entity.NoteBook;
import org.note.entity.NoteResult;

public interface NoteBookService {

	public NoteResult loadBooks(String userId);
	
	public NoteResult addBook(String bookName,String userId);
}
