package com.udacity.jwdnd.course1.cloudstorage.models;


public class File {

    private int fileId;
    private String filename;
    private String contentType;
    private String fileSize;
    private int userid;
    private byte[] fileData;

    public File(int fileId, String filename, String contentType, String fileSize, int userid, byte[] fileData) {
        this.fileId = fileId;
        this.filename = filename;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userid = userid;
        this.fileData = fileData;


    }

    public File(Integer fileId, String filename, String contentType, String fileSize, int userid, byte[] fileData) {
        this.filename = filename;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userid = userid;
        this.fileData = fileData;


    }


    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilesize() {
        return fileSize;
    }

    public void setFilesize(String fileSize) {
        this.fileSize = fileSize;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public byte[] getFiledata() {
        return fileData;
    }

    public void setFiledata(byte[] fileData) {
        this.fileData = fileData;
    }
}
