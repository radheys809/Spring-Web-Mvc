package com.own.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class UploadedFileResponse {
	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
