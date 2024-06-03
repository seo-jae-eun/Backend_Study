import java.util.ArrayList;

public class Human {
    // 이름   글자 저장
    String name;
    // 나이   숫자 저장
    int age;
    // 과자   과자를 여러개 저장
    ArrayList<Snack> snacks;

    public Human(String name, int age, ArrayList<Snack> snacks) {
        this.name = name;
        this.age = age;
        this.snacks = snacks;
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
