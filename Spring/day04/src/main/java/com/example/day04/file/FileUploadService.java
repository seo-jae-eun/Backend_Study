package com.example.day04.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    public String upload(MultipartFile file);
}