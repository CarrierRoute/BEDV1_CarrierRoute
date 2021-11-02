package com.grepp.carrierroute.common.file;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@Embeddable
public class UploadFile {

    private String uploadFileName;
    private String uuidOfFileName;

    public UploadFile(String uploadFileName, String uuidOfFileName) {
        this.uploadFileName = uploadFileName;
        this.uuidOfFileName = uuidOfFileName;
    }
}

