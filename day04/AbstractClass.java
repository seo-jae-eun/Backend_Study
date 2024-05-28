// 추상 메소드가 있으면 클래스에도 abstract 붙여줘야 된다.
public abstract class AbstractClass {
    Integer num;
    String str;
    
    void method01() {
        System.out.println("메소드01 실행");
    }


    abstract void method02();
}
