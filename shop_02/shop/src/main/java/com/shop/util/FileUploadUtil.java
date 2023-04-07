package com.shop.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static void saveFile(MultipartFile mf) throws IOException {
		// 컨테이너 생성
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext();
		// 환경변수 관리 객체 생성
		ConfigurableEnvironment env = ctx.getEnvironment();

		// property 관리 객체 생성
		MutablePropertySources prop = env.getPropertySources();

		// property 관리 객체에 property 파일 추가
		prop.addLast(
				new ResourcePropertySource("classpath:application-ncp.properties")
		);

		// property 정보 얻기
		String imgpath = env.getProperty("imglocation");


		byte [] data;
		String imgname = mf.getOriginalFilename();
		try {
			data = mf.getBytes();
			FileOutputStream fo2 = 
					new FileOutputStream(imgpath+imgname);
			fo2.write(data);
			fo2.close();
		}catch(Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

}




