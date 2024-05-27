public class Car {
    // 색상
    String color;
    // 가격
    Integer price;
    // 차종
    String type;
    // 현재속도
    Integer speed;

    // 도색한다.    색상을 입력 받아서 색상 변경
    String paint(String color){
        this.color = color;
        return color;
    }

    // 가속한다.    초와 쎄기를 입력 받아서 현재속도+초 * 쎄기
    Integer accel(Integer second, Integer str){
        speed = speed + second * str;
        return speed;
    }

    // 감속한다.    초와 쎄기를 입력 받아서 현재속도-초 * 쎄기
    Integer decel(Integer second, Integer str){
        speed = speed - second * str;
        return speed;
    }

}
