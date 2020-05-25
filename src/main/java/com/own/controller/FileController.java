package com.own.controller;

import com.own.configuration.FileUploadProperties;
import com.own.dto.UploadedFileResponse;
import com.own.exeptions.FileStorageException;
import com.own.exeptions.MyFileNotFoundException;
import com.own.file.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/file")
public class FileController {
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	@Autowired
    private FileStorageService fileStorageService;
@Autowired private FileUploadProperties  uploadProp;
	@PostMapping("/upload")
	public UploadedFileResponse uploadFile(@RequestParam("file") MultipartFile file)  {
		String fileName;
		try {
			fileName = fileStorageService.store(file);
			String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName)
					.toUriString();
			return new UploadedFileResponse(fileName, downloadUri, file.getContentType(), file.getSize());
		} catch (FileStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	@PostMapping("/uploadMultipleFiles")
    public List<UploadedFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = null;
		final String UploadLocation = uploadProp.getUploadDir();
		try {
			resource = fileStorageService.loadFileAsResource(UploadLocation + fileName);
			// Try to determine file's content type
			String contentType = null;
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			if (contentType == null) {
				contentType = "application/octet-stream";
			}
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} catch (IOException ex) {
			log.info("Could not determine file type.");
		} catch (MyFileNotFoundException e) {
			log.info("Could not determine file type.");
			e.printStackTrace();
		}
		// Fallback to the default content type if type could not be determined
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
