public class Ex05 {
    public static void main(String[] args) {
        // 제네릭
        //  클래스이름 변수이름;     이 때 클래스이름을 타입이라고 하는데
        //  제네릭은 타입을 generalize=일반화하다
        //  타입을 클래스 내부에서 결정하는게 아니라 밖에서 결정해주는 것


        Test<String, Integer> test01 = new Test<String, Integer>();
        test01.value = "글자 저장 가능";
        test01.value2 = 10;

        Test<Integer, Integer> test02 = new Test<Integer, Integer>();
        test02.value = 10;
        test02.value2 = 20;
    }
}
