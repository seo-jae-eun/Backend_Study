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