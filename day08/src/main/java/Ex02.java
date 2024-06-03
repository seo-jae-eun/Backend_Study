import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class Ex02 {
    public static void main(String[] args) {
        // 컬렉션 프레임워크
        // 프로그래밍에서 많이 사용되는 자료 구조를 자바에서 미리 구현해둔 것
        //      스택, 큐, 리스트, 트리, 해시 등
        // 인터페이스와 다형성을 이용해서 구현했기 때문에 각 컬렉션들이 메소드가 대부분 비슷
        // Iterable 인터페이스, Collection 인터페이스 또는 Map 인터페이스를 상속받아서 구현한 구현체들
        // ArrayList, HashMap, HashSet

        // 다형성
        //      하위클래스이름 변수이름 = (하위클래스이름)(new 상위클래스이름());
        //      상위클래스이름 변수이름 = new 하위클래스이름();
        List al01 = new ArrayList();
        al01.add(10);
        al01.add(20);
        al01.add(30);

        // Collection 인터페이스의 메소드
        // add() : 데이터 추가
        // remove() : 데이터 삭제
        // size() : 저장된 데이터의 수
        // contains() : 데이터가 있는지 확인
        // isEmpty() : 데이터가 없는지 확인
        // iterator() : 컬렉션의 반복자를 반환, 일반적으로 반복문과 함께 사용



        // ArrayList
        //  (배열은 크기가 정해져있다.)
        //  내부적으로 배열을 사용해서 데이터를 저장
        //  처음에 만들 때는 데이터의 크기가 정해져있고 데이터를 저장하면서 추가 공간이 필요하면 데이터 공간을 늘릴 수 있다. (처음 정한 크기 만큼 늘어남;;)
        //  데이터의 저장 순서 유지 <-> Map
        //  중복 가능 <-> Set
        //  조회가 빠르다. (index)
        //  중간에 삽입, 삭제할 때 느리다. 단, 순차적으로 삽입, 삭제할 때는 빠름(중간에 넣거나 삭제하려면 옆에 애들을 옮겨줘야해서)
        //  실제 저장된 데이터의 크기 size
        //  실제 내부적인 데이터 저장 공간의 크기는 capacity (default 10)
        //  스레드들끼리 동기화가 되지 않는다.

//        ArrayList al02 = new ArrayList();
//        al02.ensureCapacity(10); // 몇개씩 늘어날지 설정
//        al02.add("a");
//        al02.add("b");
//        al02.add("c");
//        System.out.println(al02); // 대괄호 모양
//        System.out.println(al02.size());
//
//        al02.remove("c");
//        al02.remove(1);

//        al02.add(10);
//        al02.add(20);
//        al02.add(30);
//        System.out.println(al02); // 대괄호 모양
//        System.out.println(al02.size());
//
//        al02.remove(Integer.valueOf(10)); -> 숫자 10을 제거하려면
//        al02.remove(1);

        // ArrayList 생성, List 변수에 저장 (다형성)
        List<Integer> al02 = new ArrayList<Integer>();
        al02.add(10); // 가장 마지막에 데이터 추가
        al02.add(20); // 가장 마지막에 데이터 추가
//        al02.add("asd"); // 가장 마지막에 데이터 추가
        al02.add(30); // 가장 마지막에 데이터 추가

        System.out.println(al02);       // 저장된 데이터 전체 출력
        System.out.println(al02.size());    // 저장된 데이터 수 확인
        System.out.println(getCapacity(al02));    // 실제 저장소 공간 확인

        al02.remove(Integer.valueOf(10));
        System.out.println(al02);       // 저장된 데이터 전체 출력


        al02.get(0);    // 0번째에 저장된 데이터 가져오기
        System.out.println(al02.get(0));


        System.out.println("일반 for문으로 출력");
        for (int i = 0; i < al02.size(); i++) {
            System.out.println(al02.get(i));
        }

        System.out.println("iterator로 출력");
        Iterator iterator;
        iterator = al02.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("향상된 for문으로 출력");
        for(Integer num : al02) {
            System.out.println(num);
        }

        List<Student> al03 = new ArrayList<Student>();
        Student s01 = new Student(10, "test01");
        Student s02 = new Student(20, "test02");
        Student s03 = new Student(30, "test03");

        al03.add(s01);
        al03.add(s02);
        al03.add(s03);


        // HashMap
        //  키:밸류 한쌍으로 데이터 저장하는 구조
        //  중복 안됨 (Key) -> 키:값 둘다 같으면 덮어쓰기 되서 저장 (중복 x)
        //  순서 없음
        //  삽입, 삭제, 조회 모두 빠름
        //  동기화 안됨

        Map<String, Integer> map01 = new HashMap();
        map01.put("americano",1000);  // 값을 저장할 때 키와 값을 같이 입력해서 저장한다.
        map01.put("latte",1500);
        map01.put("hotchoco",1200);

        System.out.println(map01); // 중괄호 모양

        map01.get("americano");     // 값을 가져올 때 키를 이용해서 가져온다.
        System.out.println(map01.get("americano"));

        map01.put("americano",1500);  // 이미 저장된 키로 값을 한 번 더 넣으면 기존 값에 덮어쓰기
        System.out.println(map01.get("americano"));

        System.out.println(map01.keySet()); // 키들로만 이루어진 Set 반환, Set은 중복없는 List

        System.out.println("iterator로 출력");
        Iterator keys = map01.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String)keys.next();
            System.out.print(key);
            System.out.println(map01.get(key));
        }

        System.out.println("향상된 for문으로 출력");
        for (String key : map01.keySet()) {
            System.out.print(key);
            System.out.println(map01.get(key));
        }

        // Set
        //  중복을 허용하지 않고 순서를 유지하지 않는 구조

        // Stack
        //  LIFO 구조, 마지막에 넣은 데이터가 제일 먼저 나오는 구조, 제일 먼저 넣은 데이터가 가장 마지막에 나오는 구조
        //  메모리 구조 중 하나가 Stack 구조로 동작(메소드 호출, 함수 호출)

        // Queue
        //  FIFO 구조, 처음에 넣은 데이터가 제일 먼저 나오는 구조
        //  작업 목록


        // PriorityQueue
        //  저장되는 데이터에 우선순위를 부여해서 우선 순위가 높거나 낮은 순으로 정렬해서 저장하는 구조
        PriorityQueue<Student> priorityQueue = new PriorityQueue<Student>();

        priorityQueue.add(s02); // 20
        priorityQueue.add(s01); // 10
        priorityQueue.add(s03); // 30

        System.out.println(priorityQueue.poll().name);
        System.out.println(priorityQueue.poll().name);
        System.out.println(priorityQueue.poll().name);


        // LinkedList
        //  리스트랑 큐를 모두 상속받아와서 두 가지 기능 모두 사용 가능
        //  데이터의 중간에 삽입, 삭제가 빠르다
        //  양방향 연결 리스트



        // Vector(ArrayList의 이전 버전 느낌), HashTable(HashMap의 이전 버전 느낌)
        //  메소드들에 synchronized가 달려있어 기본적으로 멀티 스레드 환경에서 동기화 제공





        // 리플렉션
        // 리플렉션 사용하면 싱글톤이 깨질 수 있음
        // Edit Configurations -> modify options -> Add VM options -> Ex02 위에 --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED 추가
        System.out.println(getCapacity(al02));

    }

    // 리플렉션
    //  실행중인 객체에 class 파일을 통해 객체의 다양한 값에 직접 접근하는 방법(변수, 메소드, 상위 클래스 등)
    //  접근제어자랑 상관없이 접근 가능
    //  클래스이름.class.get메소드();
    //  접근제어자 없이 접근하기 때문에 캡슐화 X
    //  느림
    static int getCapacity(List arrayList) {
        try {
            Field field = ArrayList.class.getDeclaredField("elementData");
            field.setAccessible(true);
            
            return ((Object[])field.get(arrayList)).length;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
