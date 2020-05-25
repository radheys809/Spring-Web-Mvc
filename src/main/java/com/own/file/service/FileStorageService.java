package com.own.file.service;

import com.own.exeptions.FileStorageException;
import com.own.exeptions.MyFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

	String store(MultipartFile file) throws FileStorageException;

	Resource loadFileAsResource(String fileName) throws MyFileNotFoundException, IOException;

}
