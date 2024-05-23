public class Pokemon {
    String name;
    Integer level;
    Integer hp;


    void attack() {
        System.out.print(name);
        System.out.println("가 공격한다.");
    }
    Integer eat() {
        hp = hp + 10;
        return hp;
    }
}
