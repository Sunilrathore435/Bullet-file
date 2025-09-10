package com.example.BulletFile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BulletFile.entity.FileEntity;
import java.time.LocalDateTime;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,Integer> {
   List<FileEntity> findByUploadedById(String uploadedById);

    List<FileEntity> findByExpiryTimeBefore(LocalDateTime now);


}
