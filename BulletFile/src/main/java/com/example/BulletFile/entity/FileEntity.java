package com.example.BulletFile.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;

    // Unique identifier for user (email for Google, login for GitHub)
    private String uploadedById;

    // Display name of the user
    private String uploadedByName;

    private LocalDateTime uploadTime;
    private LocalDateTime expiryTime;

    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private byte[] fileData;
}
