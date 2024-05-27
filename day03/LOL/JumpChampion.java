package LOL;

// Champion 클래스를 상속받음
public class JumpChampion extends Champion {
    Integer z;

    JumpChampion(Integer hp, Integer speed, Integer power, Integer defense, Integer x, Integer y) {
        super(hp, speed, power, defense, x, y);
        this.z = 0;
    }

    // 점프 기능
    // z를 30으로 변경하고 "점프 했음" 출력하고 다시 z를 0으로 변경
    void jump() {
        this.z = 30;
        System.out.println("점프 했음");
        this.z = 0;
    }
}
