package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;


    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int upload(File file){
        return fileMapper.insert(file);
    }


    public List<File> getFilesByUser(int userid){
        return fileMapper.getFilesByUser(userid);
    }


    public File getFileById(int fileId){
        return fileMapper.getFile(fileId);
    }

    public void deleteFile(int fileId){
        fileMapper.deleteFile(fileId);
    }

    public Boolean alreadyUploadedOrNone(String filename, int userid){
        if(filename.isEmpty() || filename.isBlank()){
            return true;
        }
        List<File> filesSaved = fileMapper.getFilesByUser(userid);

        for (File fileSaved : filesSaved){
            if(fileSaved.getFilename().equals(filename)){
                return true;
            }
        }

        return false;
    }
}
