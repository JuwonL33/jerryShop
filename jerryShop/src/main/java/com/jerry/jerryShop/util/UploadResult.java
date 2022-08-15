package com.jerry.jerryShop.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResult {
	
	private String fileName;
	
	private String uuid;
	
	private String folderPath;
	
	public String getImageURL() {
		try {
			return URLEncoder.encode(folderPath+"/"+uuid+fileName,"UTF-8");
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return "";
	}
	
	public String getThumbnailImageURL() {
		try {
			return URLEncoder.encode(folderPath+"/"+uuid+"/s_"+fileName,"UTF-8");
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return "";
	}
}
