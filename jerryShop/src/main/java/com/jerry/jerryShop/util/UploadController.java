package com.jerry.jerryShop.util;
/*
 * JerryShop Upload Controller for uploading files
 * Author : Jerry Juwon Lee
 * Version : 1.0
 * Date : 2022-08-10
 * Copyright (C) 2022 by Jerry, All right reserved.
 */

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
@RestController
public class UploadController {

	@Value("${jerry.upload.path}")
	private String uploadPath;
	
	//@PostMapping("/uploadFiles")
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public ResponseEntity<List<UploadResult>> uploadFiles(MultipartFile[] files) {
		
		System.out.println("들어오긴와?;;;");
		
		List<UploadResult> uploadResultList = new ArrayList<>();
		for (MultipartFile file : files) {
			
			// 이미지 파일만 업로드 가능
			if(file.getContentType().startsWith("image") == false) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			
			// 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
			String originalName = file.getOriginalFilename();
			
			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
			
			// 날짜 폴더 생성
			String folderPath = makeFolder();
			
			// UUID
			String uuid = UUID.randomUUID().toString();
			
			// 저장할 파일 이름 중간에 "_"를 이용해 구분
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + fileName;
		
			Path savePath = Paths.get(saveName);
			
			try {
				file.transferTo(savePath);

				// 썸네일 생성 -> 썸네일 파일 이름은 중간에 s_로 시작
				String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + fileName;
				
				File thumbnailFile = new File(thumbnailSaveName);
				
				// 썸네일 생성
				Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);
				
				uploadResultList.add(new UploadResult(fileName, uuid, folderPath));
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<>(uploadResultList, HttpStatus.OK);
	}
	
	@PostMapping("/removeFile")
	public ResponseEntity<Boolean> removeFile(String fileName){
		String srcFileName = null;
		
		try {
			srcFileName = URLDecoder.decode(fileName, "UTF-8");
			File file = new File(uploadPath + File.separator + srcFileName);
			
			boolean result = file.delete();
			System.out.println("원본 삭제 : " + result);
			File thumbnail = new File(file.getParent(), "s_" + file.getName());
			System.out.println("썸네일 : " + thumbnail);
			
			result = thumbnail.delete();
			System.out.println("썸네일 삭제 : " + result);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		log.info("getImage().........." + fileName);
		
		File file = new File(uploadPath +"/"+fileName);
		log.info("getImage() Thumbnail Path.........." + file);
		ResponseEntity<byte[]> result = null;
				
		try {
			
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private String makeFolder() {
		
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/DD"));
		
		String folderPath = str.replace("/", File.separator);
		
		// make folder
		File uploadPathFolder = new File(uploadPath, folderPath);
		
		if(uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		}
		
		return folderPath;
	}
	
}
