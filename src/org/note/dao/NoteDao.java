package org.note.dao;

import java.util.List;
import java.util.Map;

import org.note.entity.Note;
import org.note.entity.NoteResult;

public interface NoteDao {
	
	public List<Map> findByBookId(String bookId);
	
	public void save(Note note);
	
	public Map findByNoteId(String noteId);
	
	public void editNoteById(Note note);
	
	public Note findNoteById(String noteId);
	
	public void updateStatus(String noteId);

}
