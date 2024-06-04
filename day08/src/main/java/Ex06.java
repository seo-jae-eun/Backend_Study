import java.util.ArrayList;
import java.util.List;

public class Ex06 {
    public static void main(String[] args) {
        List<Human> humanList = new ArrayList<>();

        // 팀원들 추가
        Human h1 = new Human("서재은", 25);


        // 팀원마다 가진 과자들을 추가
        Snack s1 = new Snack("이클립스", 3000, "워터멜론향");
        Snack s2 = new Snack("이클립스", 3000, "복숭아향");
        Snack s3 = new Snack("이클립스", 3000, "딸기향");

        h1.snacks.add(s1);
        h1.snacks.add(s2);
        h1.snacks.add(s3);

        
        // 팀원을 휴먼 리스트에 추가
        humanList.add(h1);
        System.out.println(humanList.get(0).snacks);




    }
}
