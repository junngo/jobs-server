package com.hr.jobs.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class FileStore {

    @Value("${file.upload.path}")
    private String filePath;

    public String getFileFullPath(String filename) {
        return filePath + filename;
    }

    public UploadFileInfo storeFile(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty())
            return new UploadFileInfo();

        String originFileName = multipartFile.getOriginalFilename();
        String storedFileName = createStoredFileName(originFileName);
        try {
            multipartFile.transferTo(new File(getFileFullPath(storedFileName)));
        } catch (IOException e) {
            log.error("File is not save: " + e);
        }

        return new UploadFileInfo(originFileName, storedFileName);
    }

    private String createStoredFileName(String originFileName) {
        String ext = extractExt(originFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originFileName) {
        int pos = originFileName.lastIndexOf(".");
        return originFileName.substring(pos + 1);
    }
}
