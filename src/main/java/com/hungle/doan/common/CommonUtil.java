package com.hungle.doan.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.FileCopyUtils;

public class CommonUtil {
	
	/**
	 * Convert date to string with input format
	 * 
	 * @param inputDate
	 * @param dateFormat
	 * @return outputDateStr
	 */
	public static String cvDateToString(Date inputDate, String dateFormat) {

		String outputDate = StringUtils.EMPTY;
		if (inputDate != null) {
			SimpleDateFormat dateFormatOutput = new SimpleDateFormat(dateFormat);
			outputDate = dateFormatOutput.format(inputDate);
		}
		
		return outputDate;
	}
	
	/**
	 * Read Properties
	 * 
	 * @param key
	 * @return value
	 */
	public static String readProperties(String key) {

		InputStream inputStream = CommonUtil.class.getClassLoader()
				.getResourceAsStream(Constants.SYSTEM_PROPS_FILE_NAME);
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
			return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Save image to storage folder
	 * 
	 * @param parentFolderPath
	 * @param fileInBase64
	 * @param oldImagePath
	 * @return assetPath
	 * @throws IOException 
	 */
	public static String saveImage(String parentFolderPath, String fileInBase64, String oldImagePath)
			throws IOException {
		// Generate assetName with format: yyyyMMdd-HHmm-${randomStr}
		SecureRandom random = new SecureRandom();
		String randomStr = new BigInteger(130, random).toString(32).substring(0, 6);
		String assetName = cvDateToString(new Date(), Constants.DATE_FORMAT_FOR_FILE_NAME) + Constants.COMMON_HYPHEN
				+ randomStr;
		//String rootFolderPath = readProperties(Constants.PROP_ROOT_FOLDER);
		String rootFolderPath = Constants.PROP_ROOT_FOLDER;
		String assetPath = parentFolderPath + assetName + Constants.SUFFIX_IMAGE_PNG;
		String fullAssetPath = rootFolderPath + assetPath;
		// Decode File From Base64 Encoding To File Image
		Base64 decoder = new Base64(); 
		byte[] imgBytes = decoder.decode(fileInBase64);
		// Create Folder To Save Image
		File parentFolder = new File(rootFolderPath + parentFolderPath);
		if (!parentFolder.exists()) {
			parentFolder.mkdir();
		}
		if (StringUtils.isNotEmpty(oldImagePath)) {
			// Remove image if it existed
			File imageFile = new File(rootFolderPath + oldImagePath);
			if (imageFile.exists() && imageFile.length() > 0) {
				imageFile.delete();
			}
		}
		// Save File To Disk
		FileCopyUtils.copy(imgBytes, new File(fullAssetPath));
		return fullAssetPath;
	}
}
