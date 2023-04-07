package com.shop.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class CFRUtil {
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
        String clientId = env.getProperty("clientId");
        String clientSecret = env.getProperty("clientSecret");
        StringBuffer reqStr = new StringBuffer();

        String result = null;


        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = imgpath + imgname;
            File uploadFile = new File(imgFile);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/face"; // 얼굴 감지
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
                result = response.toString();
            } else {
                System.out.println("[CFRUtil] error !!!");
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println(e);
        }

        return result;
    }

    public static Map<String, String> getFaceInfoFromResult(String result) throws ParseException {
        Map<String, String> retMap = new HashMap<>();
        JSONParser jsonParser = new JSONParser();
        JSONObject globalResult = (JSONObject) jsonParser.parse(result);
        System.out.println("globalResult = " + globalResult);
        JSONArray faces = (JSONArray) globalResult.get("faces");
        System.out.println("faces = " + faces);
        JSONObject jo1 = (JSONObject) faces.get(0);
        System.out.println("jo1 = " + jo1);

        String emotion = (String) ((JSONObject) jo1.get("emotion")).get("value");
        String gender = (String) ((JSONObject) jo1.get("gender")).get("value");
        String pose = (String) ((JSONObject) jo1.get("pose")).get("value");
        String age = (String) ((JSONObject) jo1.get("age")).get("value");

        System.out.println("emotion = " + emotion);
        System.out.println("gender = " + gender);
        System.out.println("pose = " + pose);
        System.out.println("age = " + age);

        retMap.put("emotion", emotion);
        retMap.put("gender", gender);
        retMap.put("pose", pose);
        retMap.put("age", age);

        System.out.println("final ret = " + retMap);

        return retMap;
    }
}
