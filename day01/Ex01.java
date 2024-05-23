import java.sql.SQLOutput;

class Test {
	public static void main(String[] args) {
		// 변수 이름 명명법
		// 문법적으로 지켜야 하는 것
		// 1. 변수이름에서 대소문자 구분
		// 2. 예약어는 사용 X (public, private, static, void, String 등)
		// 3. 숫자로 시작하면 X
		// 4. 특수문자는 _와 $만 되고 나머지는 X (잘 안씀)

		// 사회적인 약속으로 지켜야 하는 것
		// 1. Camel Case : number of age => numberOfAge <<
		// 2. Snake Case : number of age => number_of_age


		// 변수 만드는 법
		// 클래스이름 변수이름;
		Integer num01;
		String str01;

		// 변수에 뭔가를 저장하는 법
		// 변수이름 = 저장할것;
		num01 = 123;
		str01 = "Hi";
		Integer num02 = 0;
		// 변수를 사용할 때
		// 변수이름
		System.out.println( num01 );

		System.out.println( num01 / num02 );

		System.out.println( str01 );

	}
}