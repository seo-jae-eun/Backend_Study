class Student {
    Integer age;
    String name;
    Integer wis;
    Integer sul;

    Integer study(Integer time) {
        wis = wis + 10 * time;

        return wis;
    }

    Integer drink(String type, Integer count) {
        if(type.equals("소주")) {
            sul = sul - count * 4;
        }
        if(type.equals("맥주")) {
            sul = sul - count * 1;
        }
        return sul;
    }
}
