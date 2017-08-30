package org.note.service;

import org.note.entity.Note;
import org.note.entity.NoteResult;

public interface NoteService {
	
	public NoteResult loadNotes(String bookId);
	
	public NoteResult addNote(String userId,String bookId,String noteTitle);
	
	public NoteResult showNote(String noteId);
	
	public NoteResult editNote(String noteId,String noteTitle,String noteBody);
	
	public NoteResult updateStatus(String noteId);

}
