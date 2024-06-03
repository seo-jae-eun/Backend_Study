import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Ex04 {
    public static void main(String[] args) throws IOException {
        // Map 형태로 되어 있는 글자를
        String jsonStr = "{\"id\" : 1, \"name\" : \"Anna\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        // Map 형태로 바꿔서 Map 변수에 저장
        Map<String, Object> jsonMap = objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(jsonMap);
        System.out.println(jsonMap.get("name"));

        // List 형태로 되어 있는 글자를
        String jsonStr2 = "[{\"id\" : 1, \"name\" : \"Anna\"}, {\"id\" : 2, \"name\" : \"Brian\"}]";
        // List 형태로 바꿔서 List 변수에 저장
        List<Object> list = objectMapper.readValue(jsonStr2, new TypeReference<List<Object>>() {});

        System.out.println(list);
        System.out.println(list.get(0));

        Map<String, Object> jsonMap2 = (Map<String, Object>)list.get(0);
        System.out.println(jsonMap2);

    }
}