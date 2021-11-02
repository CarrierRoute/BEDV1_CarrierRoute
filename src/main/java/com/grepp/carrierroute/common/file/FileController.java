package com.grepp.carrierroute.common.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.MalformedURLException;

@RestControllerAdvice
@RequiredArgsConstructor
public class FileController {

    private final FileStore fileStore;

    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws MalformedURLException {
        return new ResponseEntity<>(new UrlResource("file:" + fileStore.getFullPath(filename)), HttpStatus.OK);
    }
}
