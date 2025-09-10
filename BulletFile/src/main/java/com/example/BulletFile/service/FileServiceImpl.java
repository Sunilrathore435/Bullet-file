package com.example.BulletFile.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.BulletFile.entity.FileEntity;
import com.example.BulletFile.exception.FileNotFoundException;
import com.example.BulletFile.model.FileModel;
import com.example.BulletFile.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    // Upload file with userId and displayName
    public ResponseEntity<?> uploadFile(MultipartFile file, String uploadedById, String uploadedByName) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setUploadedById(uploadedById);    // unique ID
        fileEntity.setUploadedByName(uploadedByName); // display name
        fileEntity.setUploadTime(LocalDateTime.now());
        fileEntity.setExpiryTime(LocalDateTime.now().plusDays(1)); // 24 hours expiry
        fileEntity.setFileData(file.getBytes());

        fileRepository.save(fileEntity);

        FileModel fileModel = new FileModel();
        BeanUtils.copyProperties(fileEntity, fileModel);
        return ResponseEntity.ok().body(fileModel);
    }

    // Download file by ID
    public ResponseEntity<?> getFile(int id) {
        Optional<FileEntity> fileEntityOptional = fileRepository.findById(id);

        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                    .body(fileEntity.getFileData());
        } else {
            throw new FileNotFoundException("File not found");
        }
    }

    // Delete file
    public ResponseEntity<?> deleteFile(int id) {
        Optional<FileEntity> entity = fileRepository.findById(id);
        if (entity.isPresent()) {
            fileRepository.delete(entity.get());
            return ResponseEntity.ok().body("Deleted successfully");
        } else {
            throw new FileNotFoundException("File not found");
        }
    }

    // Scheduled deletion of expired files
    @Scheduled(cron = "0 0 * * * *")
    public void deleteExpiredFiles() {
        List<FileEntity> expiredFiles = fileRepository.findByExpiryTimeBefore(LocalDateTime.now());
        expiredFiles.forEach(fileRepository::delete);
        System.out.println("Deleted expired files at: " + LocalDateTime.now());
    }

    // Convert entity to model
    private FileModel convertToModel(FileEntity entity) {
        FileModel model = new FileModel();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

    // Get all files (admin or general listing)
    @Override
    public List<FileModel> getAllFiles() {
        return fileRepository.findAll().stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    // Share file
    @Override
    public ResponseEntity<?> shareFile(int id) {
        Optional<FileEntity> fileEntity = fileRepository.findById(id);
        if (fileEntity.isPresent()) {
            FileEntity file = fileEntity.get();
            FileModel fileModel = convertToModel(file);
            return ResponseEntity.ok().body(fileModel);
        } else {
            throw new FileNotFoundException("File not found");
        }
    }

    // Get files uploaded by a specific user using unique ID
    @Override
    public List<FileModel> getFilesByUser(String uploadedById) {
        List<FileEntity> entities = fileRepository.findByUploadedById(uploadedById);
        return entities.stream()
                .map(entity -> new FileModel(
                        entity.getId(),
                        entity.getFileName(),
                        entity.getUploadedByName(), // display name
                        entity.getUploadTime(),
                        entity.getExpiryTime(),
                        null // file data optional
                ))
                .collect(Collectors.toList());
    }
}
