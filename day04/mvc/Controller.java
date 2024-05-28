package mvc;

public class Controller {
    String signup() {
        Service service;
        service = new Service();

        Boolean result;
        result = false;

        try {
            result = service.signup("test01", "qwer1234");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("컨트롤러에서 처리");
        }

        if (result) {
            return "회원 가입 성공";
        } else {
            return "회원 가입 실패";
        }
    }
}
