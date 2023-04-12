package com.shop.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class OCRUtil {
	
	public static String getText(String imgname) throws IOException {
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
		String apiURL = env.getProperty("apiURL");
		String secretKey = env.getProperty("secretKey");

		/** Windows 스타일*/
		// String imageFile = imgpath +"\\"+imgname;
		/** Linux 스타일*/
		String imageFile = imgpath + imgname;
		StringBuffer response = null;

			try {
				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setUseCaches(false);
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setReadTimeout(30000);
				con.setRequestMethod("POST");
				String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
				con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
				con.setRequestProperty("X-OCR-SECRET", secretKey);

				JSONObject json = new JSONObject();
				json.put("version", "V2");
				json.put("requestId", UUID.randomUUID().toString());
				json.put("timestamp", System.currentTimeMillis());
				JSONObject image = new JSONObject();
				image.put("format", "jpg");
				image.put("name", "demo");
				JSONArray images = new JSONArray();
				images.add(image);
				json.put("images", images);
				String postParams = json.toString();

				con.connect();
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				long start = System.currentTimeMillis();
				File file = new File(imageFile);
				System.out.println("----------"+file.getName());
				System.out.println("----------"+file.getPath());
				System.out.println("----------"+file.getAbsolutePath());
				writeMultiPart(wr, postParams, file, boundary);
				wr.close();

				int responseCode = con.getResponseCode();
				BufferedReader br;
				if (responseCode == 200) {
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				String inputLine;
				response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();
				//System.out.println("REsult:");
				//System.out.println(response);
		
			} catch (Exception e) {
				System.out.println(e);
			}
		 
			return response.toString();
	}
	
	
	private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
	IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("--").append(boundary).append("\r\n");
		sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
		sb.append(jsonMessage);
		sb.append("\r\n");
	
		out.write(sb.toString().getBytes("UTF-8"));
		out.flush();
	
		if (file != null && file.isFile()) {
			out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
			StringBuilder fileString = new StringBuilder();
			fileString
				.append("Content-Disposition:form-data; name=\"file\"; filename=");
			fileString.append("\"" + file.getName() + "\"\r\n");
			fileString.append("Content-Type: application/octet-stream\r\n\r\n");
			out.write(fileString.toString().getBytes("UTF-8"));
			out.flush();
	
			try (FileInputStream fis = new FileInputStream(file)) {
				byte[] buffer = new byte[8192];
				int count;
				while ((count = fis.read(buffer)) != -1) {
					out.write(buffer, 0, count);
				}
				out.write("\r\n".getBytes());
			}
	
			out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
		}
		out.flush();
	}

	public static Map<String, String> getCompanyInfo(String result) throws ParseException {
		Map<String, String> ret = new HashMap<>();

		JSONParser jsonparser = new JSONParser();
		JSONObject globalResult = (JSONObject)jsonparser.parse(result);

		JSONArray images = (JSONArray) globalResult.get("images"); // 배열
		JSONObject jo1 = (JSONObject) images.get(0);

		JSONArray fields = (JSONArray) jo1.get("fields");
		JSONObject obj = (JSONObject) fields.get(0);
		String name = (String)obj.get("inferText");
		String keyName = (String) obj.get("name"); // 법인명
		JSONObject title = (JSONObject) jo1.get("title");
		String num = (String)title.get("inferText");
		String keyName2 = (String) title.get("name"); // 등록번호

		ret.put("name", name);
		ret.put("num", num);

		return ret;
	}
	
}
