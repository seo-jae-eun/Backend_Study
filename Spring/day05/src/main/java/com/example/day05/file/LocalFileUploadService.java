package com.example.day05.file;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

//@Service
public class LocalFileUploadService implements FileUploadService{
    @Value("${project.upload.path}")
    private String uploadPath;


    public String makeFolder() {

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = date.replace("/", File.separator);
        folderPath = uploadPath + File.separator + folderPath;

        File uploadPathFolder = new File(folderPath);
        if(uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }

    public String saveFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();

        String folderPath = makeFolder();

        String saveFileName = folderPath+ File.separator + UUID.randomUUID() +"_"+ originalName;
        File saveFile = new File(saveFileName);

        try {
            file.transferTo(saveFile);
            return saveFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public String upload(MultipartFile file) {
        return saveFile(file);
    }
}
