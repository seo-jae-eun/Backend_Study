# Backend_Study

---
## 📚 기술스택 📚
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white">
<img src="https://img.shields.io/badge/intellij idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
<br>
<br>


## 1️⃣ Java Programming
### 💗 day01 💗
```
 변수      1개만 저장

 배열      똑같은 것 여러개 저장

(구조체)   다른 것 여러개 저장

 객체      다른 것 여러개와 각 기능을 같이 저장
```

### 💗 day02 💗
```
<연습 문제>

1. 연금복권

2. 로또

3. 숫자야구

4. Binary Search Tree
```
### 💗 day03 💗
```
객체 : 현실 세상에 있는 무언가를 컴퓨터 세상으로 옮긴 것

접근 제어자 : 클래스, 변수, 메소드의 접근을 제한하는데 사용

❗ 싱글톤 : 하나의 클래스로 하나의 객체만 생성할 수 있게 만드는 디자인 패턴

상속 : 코드의 재사용성을 높이기 위해 사용

생성자 : 객체가 생성될 때 무조건 실행되는, 클래스 이름이랑 이름이 똑같은 메소드

오버로딩 : 하나의 클래스에서 메소드를 똑같은 이름으로 여러 개 만드는 것 (단, 매개변수 타입이나 개수는 달라야 함)

오버라이딩 : 자식 클래스에서 부모 클래스의 메소드를 똑같은 이름으로 다시 만드는 것

⛔ 웹 서버 : 클라이언트가 요청한 파일을 다운시켜주는 서버 (nginx)
⛔ 웹 어플리케이션 서버 : 클라이언트가 요청하면 해당하는 프로그램 코드를 실행해서 프로그램의 실행 결과를 알려주는 서버 (tomcat)
```
<img src=https://github.com/seo-jae-eun/Backend_Study/assets/82444759/22ac509b-6ba9-4265-ae3d-6cd65799013a>

### 💗 day04 💗
```
객체 지향 개념
1. 추상 클래스 : 추상 메소드를 포함하고 있는 클래스
    a. 추상 메소드 : 선언부만 있고 구현부는 없는 메소드
    
2. 인터페이스 : 일반적인 메소드는 없고 추상 메소드로만 이루어져있다.
               인터페이스로 직접 객체를 생성할 수 없고 구현체를 만들어서 객체를 생성

3. 다형성 : 하나의 객체가 여러 가지 타입을 가질 수 있는 것
(부모클래스 타입의 참조변수로 자식클래스의 인스턴스를 참조할 수 있도록 하는 것)
```
```
📡 소켓 통신 : 남의 컴퓨터에 실행 중인 남의 프로그램과의 통로를 열어주는 스트림
           (1~4계층까지 알아서 해줌)
           
입출력 스트림
1. Input Stream ⌨
    a. InputStream or FileInputStream으로 통로를 연다.
    b. InputStreamReader 글자 단위로 데이터를 읽어온다.
    c. BufferedReader 로 데이터를 하나 읽고 바로 하나를 처리하는게 아니라 버퍼에 모았다가 일정 크기가 되면 처리한다.
    
2. Out Stream 💻
    a. OutputStream ,FileOutputStream으로 통로를 연다.
    b. PrintStream 으로 출력한다.
```

### 💗 day05 💗
```
Thread : 프로세스 안에서 실질적으로 작업을 실행하는 단위
         프로세스는 적어도 한 개 이상의 Thread(Main Thread)를 가지고 있다.
         
 
💬 스레드와 소켓 통신을 활용한 채팅 프로그램 만들기 💬
 요구사항1. 스레드를 이용해서 채팅을 보내는 것과 받는 것을 무한 반복문으로 계속할 수 있게 개발하시오.
    a. 읽기 전용 스레드 (ReadThread)
    b. 쓰기 전용 스레드 (WriteThread)
 요구사항2. 요구사항1의 서버, 클라이언트는 서로 1대1 통신만 가능한 상태인데, 서버를 중계 서버로 만들어서 여러 클라이언트가 서로 메시지를 주고 받을 수 있도록 만드시오.
    a. 서버
        클라이언트 소켓을 여러 개 저장할 수 있도록 변경
        특정 클라이언트로부터 받은 메시지를 나머지 다른 클라이언트한테 전부 전송
        서버의 읽고 쓰는 스레드 추가 (ReadWriteThread)
```
### 💗 day06 💗
```dtd
⭐DTO (Data Transfer Object) : 데이터를 주고 받을 때만 사용하는 객체

⭐DAO (Data Access Object) : 데이터 접근 객체, DB에 접근하는 역할

CP (Connection Pool) : DB와 연결하는 통로를 미리 여러 개 만들어놓고 요청이 오면 빌려주고, 반납받음 -> Hikari 사용

Servlet : 사용자의 요청을 HTTP로 받아서 적절한 응답을 주는 객체

Tomcat : 클라이언트가 요청한 프로그램 코드(Servlet)를 실행해서 프로그램 결과만 알려주는 프로그램 (was)
```
### 💗 day07 💗
```
📦 DB 연결 📦
DB 연결만 한다 : 새로고침할 때마다 DB 연결이 하나 생성, 연결이 계속 쌓임
DB 연결하고 끊는다 : 새로고침할 때마다 DB 연결이 하나 생성, 다 사용하면 연결 종료

DBCP 적용 (싱글톤 x) : 새로고침할 때마다 DB 연결이 10개 생성, 연결이 계속 쌓임
DBCP 연결하고 끊는다 : 새로고침할 때마다 DB 연결이 10개 생성, 연결이 계속 쌓임

DBCP 적용 (싱글톤 o) : 처음 실행될 때만 DB 연결이 10개 생성, 10개의 연결을 다쓰면 다음 사용자는 접속 x
DBCP 연결하고 끊는다 : 처음 실행될 때만 DB 연결이 10개 생성, 다른 사용자가 반납한 연결을 돌려서 사용
```
```
👌 동시성 제어 👌
    스레드 세이프 : 각각의 스레드가 순차적으로 작업을 수행했을 때와 동시에 작업을 수행했을 때 결과가 같은 것
    <-> 동시성 문제가 있다.
    동시성 문제 해결 방법 (JAVA)
        1. synchronized : 하나의 스레드만 작업을 할 수 있도록 제어, 잘못 달아주면 성능에 영향을 미친다.
        2. volatile : 완전 동시성 제어 x
```
```
HTTP 프로토콜
    상태 : Stateless (서버가 클라이언트의 상태를 저장하지 않음)
        <-> Stateful (서버가 클라이언트의 상태를 저장함)
        
    
🍪 쿠키 : 클라이언트 프로그램에 특정 데이터를 저장해두는 것
📩 세션 : 서버 프로그램에 특정 데이터를 저장해두는 것
```
```
🔐 보안 🔐 (스프링이 알아서 해줌)
    사용자가 입력하는 모든 값들은 절대 믿으면 안됨
    👨‍💻 개발자가 해야하는 일
        - 입력값 검증
        - 로직 변경
        - 라이브러리 객체 변경
    👩‍💻 웹 개발자가 알아야 하는 보안
        - SQL Injection
        - XSS : <script></script>
        - CSRF
```
### 💗 day08 💗
<img src=https://github.com/seo-jae-eun/Algorithm/assets/82444759/af7c7845-58da-4bf9-9938-fce3751c858f>
102 서버에서 로그인하면 102 서버에만 세션이 저장되고, Haproxy를 통해 103 서버로 접속하면 로그인 유지가 되지 않음 -> 세션 불일치

```
세션 로그인 방식 (서버에 세션 저장, 클라이언트 쿠키 저장)
solution1️⃣. 세션을 DB에 저장한다.
    부하분산 했을 때 세션 불일치 문제를 해결
    DB에 부하가 많이 간다. (접속할 때마다 DB에서 체크를 해야하기 때문)
    
    1-1. 캐시 서버에 저장한다.
        부하분산 했을 때 세션 불일치 문제를 해결
        서버 비용, 관리 문제
    
solution2️⃣. WAS에 세션을 전부 복사한다. -> 세션 클러스터링
           (Tomcat에서 설정)
    부하분산 했을 때 세션 불일치 문제를 해결
    서버의 메모리가 낭비가 될 수 있다.

solution3️⃣. 클라이언트가 갔던 곳에만 다시 갈 수 있게 부하분산한다. -> 스티키 세션 (처음갔던 서버로만 계속 접속)
           (Haproxy에서 설정)
    부하분산 했을 때 세션 불일치 문제를 해결
    부하 분산이 원하는 대로 되지 않을 수도 있다.

토큰 로그인 방식
solution4️⃣. 세션을 안쓰는 방법 -> 다 클라이언트한테 저장
    - Access Token
    - Refresh Token
```

📜 열거형 📜
<br>일정 형식을 가지는 값이 여러 개 있을 때 미리 열거해두는 파일

👑 컬렉션 프레임워크 👑
<br>&nbsp;&nbsp;&nbsp;&nbsp;프로그래밍에서 많이 사용되는 자료 구조를 자바에서 미리 구현해둔 것
<br>&nbsp;&nbsp;&nbsp;&nbsp;스택, 큐, 리스트, 틑리, 해시 등
<br>&nbsp;&nbsp;&nbsp;&nbsp;인터페이스와 다형성을 이용해서 구현했기 때문에 각 컬렉션들의 메소드가 대부분 비슷
<br>&nbsp;&nbsp;&nbsp;&nbsp;Iterable 인터페이스, Collection 인터페이스 또는 Map 인터페이스를 상속받아서 구현한 구현체들
<details>
  <summary>ArrayList</summary>
&nbsp;&nbsp;&nbsp;&nbsp;1. 내부적으로 배열을 사용해서 데이터를 저장
<br>&nbsp;&nbsp;&nbsp;&nbsp;2. 처음에 만들 때는 데이터의 크기가 정해져 있고,
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;데이터를 저장하면서 추가 공간이 필요하면 데이터 공간을 늘릴 수 있다.
<br>&nbsp;&nbsp;&nbsp;&nbsp;3. 데이터의 저장 순서 유지
<br>&nbsp;&nbsp;&nbsp;&nbsp;4. 중복 가능
<br>&nbsp;&nbsp;&nbsp;&nbsp;5. 조회가 빠르다
<br>&nbsp;&nbsp;&nbsp;&nbsp;6. 중간에 삽입, 삭제할 때 느리다. 단, 순차적으로 삽입, 삭제할 때는 빠르다.
<br>&nbsp;&nbsp;&nbsp;&nbsp;7. 실제 저장된 데이터의 크기는 size, 실제 내부적인 데이터 저장 공간의 크기는 capacity (default 10)
<br>&nbsp;&nbsp;&nbsp;&nbsp;8. 스레드들끼리 동기화 되지 않는다.
</details>
<details>
  <summary>HashMap</summary>
&nbsp;&nbsp;&nbsp;&nbsp;1. Key:Value 한쌍으로 데이터를 저장하는 구조
<br>&nbsp;&nbsp;&nbsp;&nbsp;2. 중복 안됨
<br>&nbsp;&nbsp;&nbsp;&nbsp;3. 순서 없음
<br>&nbsp;&nbsp;&nbsp;&nbsp;4. 삽입, 삭제, 조회 모두 빠르다.
<br>&nbsp;&nbsp;&nbsp;&nbsp;5. 동기화 안된다.
</details>
● HashSet

```
    Collection 인터페이스의 메소드
    - add()       : 데이터 추가
    - remove()    : 데이터 삭제
    - size()      : 저장된 데이터의 수
    - contains()  : 데이터가 있는지 확인
    - isEmpty()   : 데이터가 없는지 확인
    - iterator()  : 컬렉션의 반복자를 반환, 일반적으로 반복문과 함께 사용
```


❓ 제네릭 ❓
<br>타입을 generalize=일반화하다, 타입을 클래스 내부에서 결정하는게 아니라 밖에서 결정해주는 것

### 💗 day09 💗
```
🙈 어노테이션 : 클래스, 메소드, 변수 위에 달아주면 어노테이션에 구현한 기능대로 동작하도록 하는 문법
            자바에서 @어노테이션이름 문법

👀 람다식 : 메소드를 간단하게 표현한 것, 메소드의 이름이 필요 없기 때문에 람다식은 익명 함수(Anonymous Function)의 한 종류
            익명 함수(Anonymous Function)를 생성하기 위한 식
            함수형 프로그래밍을 지원하기 위한 기능
            불필요한 코드를 줄이고, 가독성을 높이기 위해 사용
         
            // 기존 메소드 생성
            반환클래스이름 메소드이름(매개변수클래스이름 매개변수이름) {
               실행코드;
               return 반환값;
            }
         
            // 람다식 생성
            (매개변수클래스이름 매개변수이름) -> {
               실행코드;
               return 반환값;
            }
 
 📠 함수형 인터페이스 : 추상 메소드를 단 하나만 가지고 있는 인터페이스 (@FunctionalInterface)
                       객체지향 프로그래밍 언어인 자바는 객체가 없으면 함수를 실행할 수 없기 때문에 함수를 만들기 위해 함수형 인터페이스를 만들어서 사용
 
 
 🎏 Stream API : 데이터를 처리하는데 자주 사용되는 함수들을 정의
                  데이터를 추상화하여 다양한 방식으로 저장된 데이터를 읽고 쓰기 위한 공통된 방법을 제공
                  특징
                    1. 원본 데이터 변경 x
                    2. 재사용이 가능한 컬렉션과는 다르게 단 한 번만 사용
                    3. 병렬 처리를 지원하기 때문에 성능이 좋음
                  사용방법
                    1. 스트림 생성
                    2. 중개 연산(필터, 맵 등)
                    3. 최종 연산
```
### 💗 day10 💗
📦 **자료 구조 : 데이터들을 저장할 때 그냥 막 저장하는게 아니라 효율적으로 특정 구조에 맞춰서 저장**

```
📚 스택 📚
    후입선출(LIFO) 방식으로 가장 최근에 들어온 데이터가 가장 먼저 나간다.
    데이터를 삽입, 삭제하는 연산이 한쪽에서만 진행된다.
        - isEmpty() : 스택에 값이 모두 비어있는지 확인
        - isFull() : 스택에 값이 모두 저장되어 있는지 확인
        - peek() : 제일 마지막에 저장된 값 확인
        - push() : 데이터를 저장하는 연산
        - pop() : 데이터를 삭제하는 연산
        - display() : 스택에 저장된 모든 데이터를 출력하는 기능
    스택의 활용
        1. 데이터 거꾸로 정렬 : reverse()
        2. 중위식을 후위식으로 변경 : postfix()
        3. 후위식을 계산 : postfixCalc()
        

📄 리스트 📄
    순서를 가지도록 데이터를 나열한 것
    리스트에서는 각각의 요소를 노드라고 한다.
    노드는 데이터와 다음 노드로 이루어져 있다.
        - display() : head에서부터 data를 순서대로 출력
        - insertFirst() : 맨 처음에 데이터를 추가하는 기능
        - insertLast() : 맨 마지막에 데이터를 추가하는 기능
        - insert() : 원하는 순서에 데이터를 추가하는 기능 (index를 같이 전달받음)
        - removeFirst() : 맨 처음 데이터를 삭제하는 기능
        - removeLast() : 맨 마지막 데이터를 삭제하는 기능
        - remove() : 원하는 순서의 데이터를 삭제하는 기능 (index를 같이 전달받음)

```

### 💗 day11 💗
```
🌲 트리 🌲
    계층을 가지도록 데이터를 나열한 것
    트리에서는 각각의 요소를 노드라고 한다.
    노드는 데이터와 자식 노드들로 이루어져 있다.
    
    이진탐색트리(BST)
        데이터를 저장할 때 큰 걸 오른쪽에 작은 걸 왼쪽에 저장
            - add() : 데이터를 추가하는 기능 (반복문 이용)
            - add2() : 데이터를 추가하는 기능 (재귀 이용)
            
    Trie(트라이)
        문자열을 저장하고 효율적으로 탐색하기 위한 트리 형태의 자료구조
            - insert() : 문자열을 저장하는 기능, 삽입 문자열의 마지막 노드에 삽입 개수 표시
            - search() : 특정 단어를 검색하는 기능
            - count() : 특정 단어의 개수를 반환하는 기능 (예, TE?T의 개수 반환)
```