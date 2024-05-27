package LOL;

public class Champion {
    // 레벨
    private Integer level;
    // 체력
    private Integer hp;
    // 이동속도
    private Integer speed;
    // 공격력
    private Integer power;
    // 방어력
    private Integer defense;
    // x좌표
    private Integer x;
    // y좌표
    private Integer y;
    // 아이템 배열
    private String[] items;

    // 생성자(체력, 이동속도, 공격력, 방어력, x좌표, y좌표)
    //      입력받은 값으로 각 변수 셋팅, 레벨은 기본적으로 1,
    //      아이템 배열은 기본적으로 크기가 6인 배열
    Champion(Integer hp, Integer speed, Integer power, Integer defense, Integer x, Integer y) {
        this.level = 1;
        this.hp = hp;
        this.speed = speed;
        this.power = power;
        this.defense = defense;
        this.x = x;
        this.y = y;
        this.items = new String[6];
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    // 이동한다(x, y)
    //      입력받은 xy로 내 x좌표, y좌표
    void move(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    // 공격한다(챔피언)
    //      입력받은 챔피언의 체력이 내 공격력에서 입력받은 챔피언의 방어력을 뺀 만큼 줄어든다.
    void attack(Champion target) {
        System.out.print("공격당한 챔프 체력: " + target.hp + " -> ");
        target.hp = target.hp - (this.power - target.defense);
        System.out.println(target.hp);
    }
}
