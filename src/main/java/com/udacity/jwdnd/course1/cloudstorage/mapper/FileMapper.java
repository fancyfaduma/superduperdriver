package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT into FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{filename}, #{contentType}, #{fileSize}, #{userid}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty= "fileId")
    int insert(File file);

    @Select("SELECT * from Files WHERE userid = #{userid}")
    List<File> getFilesByUser(int userid);

    @Select("SELECT * from Files WHERE fileId = #{fileId}")
    File getFile(int fileId);

    @Delete("DELETE from Files WHERE fileId = #{fileId}")
    void deleteFile(int fileId);

}
