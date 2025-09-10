package com.example.BulletFile.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.BulletFile.model.FileModel;

public interface FileService {

    // Upload a file by user with unique ID and display name
    ResponseEntity<?> uploadFile(MultipartFile file, String uploadedById, String uploadedByName) throws IOException;

    // Get a file by its ID
    ResponseEntity<?> getFile(int id);

    // Delete a file by its ID
    ResponseEntity<?> deleteFile(int id);

    // Delete expired files automatically
    void deleteExpiredFiles();

    // Get all files (for admin maybe)
    List<FileModel> getAllFiles();

    // Share a file
    ResponseEntity<?> shareFile(int id);

    // Get files uploaded by a specific user using unique ID
    List<FileModel> getFilesByUser(String uploadedById);
}
