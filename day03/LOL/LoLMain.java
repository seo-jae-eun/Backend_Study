package LOL;

public class LoLMain {
    public static void main(String[] args) {
        // 생성자(체력, 이동속도, 공격력, 방어력, x좌표, y좌표)
        Champion 럭스 = new Champion(80, 80, 80, 30, 1, 1);
        Champion 빅토르 = new Champion(75, 85, 90, 25, 20, 20);
        Champion 유미 = new Champion(50, 50, 60, 20, 20, 20);
        Champion 바루스 = new Champion(78, 82, 93, 23, 100, 100);
        Champion 노틸러스 = new Champion(99, 83, 30, 70, 105, 105);
        Champion 아트록스 = new Champion(90, 89, 94, 60, 600, 600);

        럭스.move(10000, 9999);
        // 변수에 private 때문에 직접 접근 못함
        System.out.println("챔피언 위치: " + 럭스.getX() + ", " + 럭스.getY());
        바루스.attack(노틸러스);

        JumpChampion 트리스타나 = new JumpChampion(72, 88, 92, 20, 30, 30);
        트리스타나.jump();

    }
}
