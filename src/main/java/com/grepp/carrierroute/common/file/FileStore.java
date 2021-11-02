package com.grepp.carrierroute.common.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) {
        return multipartFiles.stream()
                .filter(multipartFile -> !multipartFile.isEmpty())
                .map(this::storeFile)
                .collect(Collectors.toList());
    }

    public UploadFile storeFile(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();

        String storeFileName = createStoreFileName(originalFilename);
        try {
            multipartFile.transferTo(new File(getFullPath(storeFileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new UploadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String extension = extractExtension(originalFilename);
        return uuid + "." + extension;
    }

    private String extractExtension(String originalFilename) {
        int index = originalFilename.lastIndexOf(".");
        String extension = originalFilename.substring(index + 1);
        return extension;
    }
}
