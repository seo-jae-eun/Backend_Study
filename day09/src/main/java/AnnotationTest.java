

//@CustomAnnotation     ->  CustomAnnotation의 @Target을 메소드로만 설정했기 때문에 클래스에는 사용할 수 없다.
public class AnnotationTest {
    @CustomAnnotation(enabled = true)
    public void method1() {
        System.out.println("method1 is executed.");
    }

    @CustomAnnotation(enabled = true)
    public void method2() {
        System.out.println("method2 is executed.");
    }
    @CustomAnnotation(enabled = false)
    public void method3() {
        System.out.println("method3 is executed.");
    }
}
