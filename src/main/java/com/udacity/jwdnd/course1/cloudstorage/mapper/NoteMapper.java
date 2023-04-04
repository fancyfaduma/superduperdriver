package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Insert("INSERT into NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty= "noteId")
    int insert(Note note);

    @Select("SELECT * from Notes WHERE userid = #{userid}")
    List<Note> getNotesByUser(int userid);

    @Select("SELECT * from Notes WHERE noteid = #{noteId}")
    Note getNote(int noteId);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription}, userid = #{userid} "
            +"WHERE noteid=#{noteId}")
    void updateNote(Note note);

    @Delete("DELETE from Notes WHERE noteid = #{noteId}")
    void deleteNote(int noteId);
}
