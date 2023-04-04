package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int addNote(Note note){
        return noteMapper.insert(note);
    }

    public List<Note> getNotesByUser(int userid){
        return noteMapper.getNotesByUser(userid);
    }

    public void updateNote(Note note)
    {
        noteMapper.updateNote(note);
    }

    public void deleteNote(Note note){
        noteMapper.deleteNote(note.getNoteId());
    }
}
