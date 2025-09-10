package com.example.BulletFile.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileModel {
    
    private int id;
    private String fileName;
    private String uploadedBy;
    private LocalDateTime uploadTime;
    private LocalDateTime expiryTime;
    private byte[] filedata;

    // Constructor without file data
    public FileModel(int id, String fileName, String uploadedBy, LocalDateTime uploadTime, LocalDateTime expiryTime) {
        this.id = id;
        this.fileName = fileName;
        this.uploadedBy = uploadedBy;
        this.uploadTime = uploadTime;
        this.expiryTime = expiryTime;
        this.filedata = null;
    }
}
