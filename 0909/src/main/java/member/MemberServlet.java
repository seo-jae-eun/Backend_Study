package member;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.BaseResponse;
import config.BaseResponseMessage;
import member.request.PostMemberReq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            json.append(line);
        }
        ObjectMapper mapper = new ObjectMapper();
        PostMemberReq dto = mapper.readValue(json.toString(), PostMemberReq.class);

        System.out.println(json.toString());
        
        System.out.println(dto.getId());
        System.out.println(dto.getPw());
        
        // 회원 가입하는 DAO의 메소드 실행


        // 결과를 json 형식의 응답

    }
}
