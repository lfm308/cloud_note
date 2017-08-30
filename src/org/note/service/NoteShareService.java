package org.note.service;

import org.note.entity.NoteResult;

public interface NoteShareService {
	
	public NoteResult shareDao(String noteId);
	
	public NoteResult searchShareNotes(String key);

}
