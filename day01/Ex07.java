public class Ex07 {
    public static void main(String[] args) {
        // 배열
        //      같은 형식의 변수를 여러개 저장하는 것

        // 배열 변수 생성하는 법
        // 클래스이름[] 배열변수이름;
        Integer[] nums;
        
        // 배열 객체 생성
        // 배열변수이름 = new 클래스이름[크기];
        nums = new Integer[5];

        // 배열에 값 저장하는 법
        // 배열변수이름[인덱스번호] = 값;
        nums[0] = 10;
        nums[1] = 20;
        nums[2] = 30;
        nums[3] = 40;
        nums[4] = 50;
//        nums[5] = 60; // 5개까지만 쓰기로 했는데 6개 써서 에러남


        nums = new Integer[500];
        for(int i = 0; i < 500; i++) {
            nums[i] = i + 10;
        }
        System.out.println(nums[328]);


        // 지우포켓몬 6마리만 가질 수 있다.
        // 포켓몬 객체를 6개 저장할 수 있는 지우포켓몬 배열 변수를 만드시오.
        Pokemon[] pokemons = new Pokemon[6]; // 배열을 생성
        pokemons[0] = new Pokemon();
        pokemons[1] = new Pokemon();
        pokemons[2] = new Pokemon();
        pokemons[3] = new Pokemon();
        pokemons[4] = new Pokemon();
        pokemons[5] = new Pokemon(); // 객체를 생성??


        pokemons[0].name = "피카츄";
        pokemons[0].level = 10;
        pokemons[0].hp = 100;

        pokemons[1].name = "리자몬";
        pokemons[1].level = 60;
        pokemons[1].hp = 400;

        pokemons[2].name = "잠만보";
        pokemons[2].level = 60;
        pokemons[2].hp = 700;

        pokemons[3].name = "이브이";
        pokemons[3].level = 5;
        pokemons[3].hp = 30;

        pokemons[4].name = "고라파덕";
        pokemons[4].level = 1;
        pokemons[4].hp = 10;

        pokemons[5].name = "뮤츠";
        pokemons[5].level = 99;
        pokemons[5].hp = 999;

        // 지우가 가진 포켓몬 중에 체력이 300이상인 포켓몬의 이름을 출력
        // 0부터 5까지 1씩 커지면서 반복
        //      만약에 현재 반복중인 순서 번호의 포켓몬의 체력이 300보다 크면
        //          포켓몬의 이름 출력
        for(Integer i = 0; i < 6; i++) {
            if(pokemons[i].hp > 300) {
                System.out.println(pokemons[i].name);
            }
        }
    }
}
