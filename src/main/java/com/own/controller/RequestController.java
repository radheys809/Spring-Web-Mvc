package com.own.controller;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.own.constants.CacheNames;
import com.own.dto.Response;
import com.own.dto.UserDto;
import com.own.exeptions.EmptyResource;
import com.own.service.UserService;
import com.own.service.impl.ErrorResponseHandler;
import com.own.utils.AppStringUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest")
public class RequestController {
	private static final Logger log = LoggerFactory.getLogger(RequestController.class);

	@Autowired private UserService userService;
	@Autowired private ErrorResponseHandler responseHandler;
	
	@ApiOperation(consumes = MediaType.APPLICATION_JSON_VALUE, value = "",response = String.class,produces = MediaType.APPLICATION_JSON_VALUE,ignoreJsonView = true)
	
	@PostMapping(value = "/welcome",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public String welcome(@RequestBody Map<String, String> reqData) {
		System.out.println("Radhey");
		return "hello :: "+reqData.get("userName");
		
	}
	@PostMapping(value="/login",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Object login(@RequestBody Map<String, String> data) {
		System.out.println("Radhey");
		 Map<String, String> respData=new HashMap<>();
		 UserDto userData=userService.getByuserName(data.get("userName"));
		 if (AppStringUtils.isEmpty(userData)) {
			 respData.put("errorMessage", "userNotFound");
		} else {
			respData.put("msisdn", userData.getMobile());
			respData.put("email", userData.getEmail());
			respData.put("errorMessage", "Success");
		}
		 respData.put("name", "Radhey");
		 respData.put("city", "Noida");
		 respData.put("country", "India");
		 respData.put("profession", "Engineer");
		 com.hazelcast.spring.cache.HazelcastCacheManager  cacheManager=new HazelcastCacheManager(Hazelcast.getHazelcastInstanceByName("hazelcast-cache"));
		 Cache cache =cacheManager.getCache(CacheNames.USERNAME.Name);
		System.out.println("Cache Data:"+cache.getNativeCache());
		
				return respData;
	}
	@GetMapping(value = "/fetch/{name}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto fetchByName(@PathVariable("name") String name) {
		System.out.println("path Variable");
		return userService.getByuserNameUsingSP(name);
		
	}
	@DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteUserDataById(@PathVariable("id") long id){
		log.info("deleteing the record of id:{}",id);
		String result="1";
		try {
			result=userService.deleteUserById(id);
			return new ResponseEntity<>(responseHandler.deleteUserResponse(result),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(responseHandler.deleteUserResponse(result),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value = "/generateReport",produces = {MediaType.APPLICATION_PDF_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> generatePdfReport(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
		log.info("generating pdf report");
		HttpHeaders headers = new HttpHeaders();
		ByteArrayInputStream report =null;;
		try {
			 report=userService.generateReportAsPdf(page, size);
	        headers.add("Content-Disposition", "inline; filename=users.pdf");
	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(report));
		}catch (EmptyResource e) {
			log.error("PDF Generation Exception {}", e.getMessage());
			return ResponseEntity
	                .ok()
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(responseHandler.reportNotGenerated(e.getCode(),e.getMessage()));
		}catch (Exception e) {
			log.error("PDF Generation Exception {}", AppStringUtils.isEmpty(e.getMessage())?"ERROR":e.getMessage());
		}
		return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseHandler.genericError());		
	}

}
