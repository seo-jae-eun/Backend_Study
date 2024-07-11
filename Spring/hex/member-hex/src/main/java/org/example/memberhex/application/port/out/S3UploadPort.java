package org.example.memberhex.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface S3UploadPort {
    String uploadMemberProfileImage(MultipartFile file);
}
