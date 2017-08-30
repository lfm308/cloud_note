package org.note.dao;

import java.util.List;

import org.note.entity.Share;

public interface NoteShareDao {
	
	public void insertIntoShare(Share share);
	
	public Share findByNoteId(String noteId);
	
	public void editShare(Share share);
	
	public List<Share> findSharesByKey(String key);

}
