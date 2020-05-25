package com.own.file.service.impl;

import com.own.configuration.FileUploadProperties;
import com.own.exeptions.FileStorageException;
import com.own.exeptions.MyFileNotFoundException;
import com.own.file.service.FileStorageService;
import com.own.utils.AppStringUtils;
import com.own.utils.FileEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class FileStorageServiceImpl implements FileStorageService{
	private final Path fileStorageLocation;
	@Autowired private FileEncryption fileEncryption;
	@Autowired
    public FileStorageServiceImpl(FileUploadProperties fileStorageProperties) throws FileStorageException {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
	@Override
	public String store(MultipartFile file) throws FileStorageException {
		// Normalize file name
		String fileName = null;
		try {
			Assert.notNull(file, "File data is blank");
			AppStringUtils.isNullEmpty(file);
			fileName = AppStringUtils.cleanPath(file.getOriginalFilename());
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			byte imageData[] = file.getBytes();
			byte [] encryptedFile=fileEncryption.encrypt(FileEncryption.PRIVATE_SEC_KEY, imageData);
//			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			Files.write(targetLocation, encryptedFile);
			return fileName;
		} catch (IOException e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}
	}

	public Resource loadFileAsResource(final String fileName) throws MyFileNotFoundException, IOException {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            MultipartFile file=(MultipartFile) resource.getFile();
            
            File outputFile=new File(filePath.getFileName().toString());
            byte []byteData=file.getBytes();
            byte []plainBytes=fileEncryption.decrypt(FileEncryption.PRIVATE_SEC_KEY, byteData);
            	FileOutputStream fileOutputStream= new    FileOutputStream(outputFile)   ;      
           fileOutputStream.write(plainBytes);
           fileOutputStream.close();
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " +fileName, ex);
        }
    }

}
