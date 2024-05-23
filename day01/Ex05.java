public class Ex05 {
    public static void main(String[] args) {
        // 객체 지향 프로그램, Object Oriented Program
        
        // 객체 : 현실 세상에 있는 유형 또는 무형(꿈 같은..)의 무언가를 컴퓨터 세상에 구현한 것
        //          객체는 속성(변수)과 기능(메소드,행동)으로 이루어져 있다.
        //          공통된 특징으로 객체를 만든다.
        //      예) 이름, 나이를 저장해서 학생 객체를 만든다.
        //          객체를 만들기 위한 공통된 틀(형식)을 클래스라고 한다.

        // ORM (객체 관계형데이터베이스 맵핑)

        Student sje;
        sje = new Student();

        sje.age = 25;
        sje.name = "서재은";
        sje.computer = new Computer();
        sje.computer.cpu = 8;
        sje.computer.ram = 16;
        sje.computer.hdd = 256;
        sje.computer.제조사 = "삼성";
        sje.computer.price = 100;
        sje.코딩실력 = 100;
        System.out.println(sje.코딩실력);
        sje.술();
        sje.술();
        sje.술();
        sje.술();
        System.out.println(sje.코딩실력);
        sje.공부();
        sje.공부();
        System.out.println(sje.코딩실력);



        Student sgy;
        sgy = new Student();
        sgy.age = 23;
        sgy.name = "서가영";
        sgy.computer = new Computer();
        sgy.computer.cpu = 16;
        sgy.computer.ram = 32;
        sgy.computer.hdd = 512;
        sgy.computer.제조사 = "개조";
        sgy.computer.price = 80;


        // 3종류의 커피 객체
        Coffee c1;
        c1 = new Coffee();
        c1.store = "스타벅스";
        c1.price = 4500;
        c1.kind = "아메리카노";

        Coffee c2;
        c2 = new Coffee();
        c2.store = "이디아";
        c2.price = 4000;
        c2.kind = "라떼";

        Coffee c3;
        c3 = new Coffee();
        c3.store = "투썸플레이스";
        c3.price = 5000;
        c3.kind = "라떼";
        


    }
}
