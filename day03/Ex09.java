public class Ex09 {
    // 생성자
    public static void main(String[] args) {
        // 생성자
        // 클래스로 객체를 생성할 때 실행되는 클래스이름과 똑같은 이름의 메소드
        Champion c;
        c = new Champion();

        System.out.println(c.name);
        System.out.println(c.hp);
        System.out.println(c.def);

        Champion 문도;
        문도 = new Champion("문도", 120);
        System.out.println(문도.name);
        System.out.println(문도.hp);
        System.out.println(문도.def);


    }
}
