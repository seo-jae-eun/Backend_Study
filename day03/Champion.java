public class Champion {
    String name;
    Integer hp;
    Integer def;

    // 기본 생성자
    Champion() {
        name = "챔피언";
        hp = 100;
        def = 10;
    }

    // 오버 로딩
    Champion(String inputName, Integer InputHp) {
        name = inputName;
        hp = InputHp;
        def = 10;
    }
}
