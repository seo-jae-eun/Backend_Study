import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Ex03 {
    public static void main(String[] args) throws IOException {

        URL url = new URL("https://store.ohou.se/api/home/module/6618e15779abdc629cb40806"); // 요청을 보낼 URL을 정의
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // URL에 대한 연결을 염

        connection.setRequestMethod("POST"); // HTTP 요청 방식을 POST로 설정
        connection.setDoOutput(true); // 요청 본문에 데이터를 전송할 수 있도록 함
        connection.setRequestProperty("Content-Type", "application/json"); // 요청의 컨텐츠 타입을 JSON으로 설정

        String jsonInputString = "{\"request_token\":\"CAoSBggOEgIICCIECBQQAirJAQoCCAQSwgEKGDY0ZGIxZTUyZjI1MjlhMGNiYjk4MGQ0NhJVCAQqURIZChfsmKTripjsnZgg7LaU7LKcIOyDge2SiCABKjIKDwoFCgMxLjASBgoEMi41NhIPCgUKAzEuMBIGCgQ0LjE3Gg4KBQoDMS4wEgUKAzQuMBoGCAUSAggDKkcKEENBUVNCZ2dGRWdJSUF3PT0aGAgBEAEaByNGN0Y5RkEiBDEwLjAqAzAuMCIZ7Iqk7Yag7Ja07ZmIIOykkeqwhOq0keqzoCqrAQoCCAwSpAEKGDY0ZGIxZTU0ZjI1MjlhMGNiYjk4MGQ3MRI4CAIaNBIyCg4KBQoDMi4wEgUKAzUuMBIPCgUKAzEuMBIGCgQxMC4wGg8KBQoDMS4wEgYKBDEwLjAaBggCEgIIASpGChBDQUlTQmdnQ0VnSUlBUT09GhgIARABGgcjRjdGOUZBIgQxMC4wKgMwLjAiFuyKpO2GoOyWtO2ZiCDtgLXrqZTribQoASrjAQoCCBQS3AEKGDY0ZGIxZTU0ZjI1MjlhMGNiYjk4MGQ3MhJ6CAQqdhIOCgzsmKTripjsnZjrlJwaMAojCiFodHRwczovL3N0b3JlLm9ob3Uuc2UvdG9kYXlfZGVhbHMSCeuNlOuztOq4sCoyCg8KBQoDMS4wEgYKBDIuNTYSDwoFCgMxLjASBgoENC4xNxoOCgUKAzEuMBIFCgM0LjAaBggEEgIIAio8ChBDQVFTQmdnRUVnSUlBZz09EAEaGAgBEAEaByNGN0Y5RkEiBDEwLjAqAzAuMCIM7Jik64qY7J2Y65ScKtQBCgIILBLNAQoYNjRkYjFlNTJmMjUyOWEwY2JiOTgwZDRhEmcICVJjEiMKIeyHvO2VkSDsi6Tsi5zqsIQg7J246riwIOqygOyDieyWtBoUChIwNi4wMyAxNOyLnCDquLDspIAiJgoOCgUKAzUuMBIFCgMyLjASDgoFCgM1LjASBQoDMi4wGgQKABIAGgYIChICCAkqQAoQQ0FrU0JnZ0tFZ0lJQ1E9PRABGhgIARABGgcjRjdGOUZBIgQxMC4wKgMwLjAiEOyduOq4sCDqsoDsg4nslrQyGwoXZjVmeVR5SjNOTExHRHNZY0JOTDhmM04QFA\"}";
        // 서버로 전송할 JSON 형식의 문자열을 정의

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8"); // JSON 문자열을 바이트 배열로 변환
            os.write(input, 0, input.length); // 변환된 바이트 배열을 출력 스트림을 통해 전송
        }

        int responseCode = connection.getResponseCode(); // 서버로부터 받은 HTTP 응답 코드를 가져옴
//        System.out.println("Response Code : " + responseCode); // 응답 코드를 콘솔에 출력

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); // 서버로부터의 응답을 읽기 위한 BufferedReader를 생성
        String inputLine;
        StringBuffer response = new StringBuffer(); // 서버 응답을 저장할 StringBuffer 객체를 생성

        while ((inputLine = in.readLine()) != null) { // 서버 응답의 끝까지 한 줄씩 읽어 들임
            response.append(inputLine); // 읽은 데이터를 StringBuffer 객체에 추가
        }
        in.close(); // BufferedReader를 닫아 리소스를 해제

//        System.out.println(response.toString());  // 받은 JSON 응답을 콘솔에 출력


        // 10번 상품의 이름 + 가격들(3가지)
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonResponse = objectMapper.readValue(response.toString(), new TypeReference<Map<String, Object>>() {
        });
//        System.out.println(jsonResponse.get("content"));

        Map<String, Object> jsonContent = (Map<String, Object>)jsonResponse.get("content");
//        System.out.println(jsonContent.get("slots"));

        List<Object> jsonSlots = (List<Object>)jsonContent.get("slots");
//        System.out.println(jsonSlots.get(10));
        Map<String, Object> json10 = (Map<String, Object>)jsonSlots.get(10);
//        System.out.println(json10);

        Map<String, Object> json10goods = (Map<String, Object>)json10.get("goods");
        System.out.println(json10goods.get("name"));
        Map<String, Object> json10price = (Map<String, Object>)json10goods.get("price");
        System.out.println(json10price.get("originalPrice"));
        System.out.println(json10price.get("sellingPrice"));
        System.out.println(json10price.get("discountRate"));
    }
}
