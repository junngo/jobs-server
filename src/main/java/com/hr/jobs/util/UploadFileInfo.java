package com.hr.jobs.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadFileInfo {
    private String originFilename;
    private String storedFilename;
}
