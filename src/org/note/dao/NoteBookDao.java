package org.note.dao;

import java.util.List;

import org.note.entity.NoteBook;

public interface NoteBookDao {
	
	public List<NoteBook> findByUser(String userId);
	
	public void save(NoteBook book);

}
