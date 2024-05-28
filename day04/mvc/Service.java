package mvc;

public class Service {
    public Boolean signup(String id, String pw) throws Exception {
        Repository repository;
        repository = new Repository();

        Integer result;

        result = 10 / 0;


        if(id == null || pw == null) {
            System.out.println("회원 가입 실패");
            return false;
        }


        return repository.save(id, pw);
    }
}
