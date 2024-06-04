import java.lang.reflect.Method;

public class Ex01 {
    public static void main(String[] args) {
        // 스프링 어노테이션
        //  @RestController, @Controller, @Service, @Repository, @Bean, @Configuration, @Component
        //  @Autowired
        //  @SpringTest, @Test

        // Lombok
        //  @Getter, @Setter, @Data, @Builder

        // 어노테이션
        //  자바에서 @어노테이션이름 문법
        //  클래스, 메소드, 변수 위에 달아주면 어노테이션에 구현한 기능대로 동작하도록 하는 문법
        AnnotationTest myClass = new AnnotationTest();
        Method[] methods = AnnotationTest.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(CustomAnnotation.class)) {
                CustomAnnotation annotation = method.getAnnotation(CustomAnnotation.class);
                if (annotation.enabled()) {
                    try {
                        method.invoke(myClass);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    method.invoke(myClass);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
