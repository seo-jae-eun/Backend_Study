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

### 💗 day03 💗
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