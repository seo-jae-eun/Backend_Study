public class Student implements Comparable<Student> {
    Integer age;
    String name;

    public Student(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        if(this.age < o.age) {
            return 1;
        } else if(this.age > o.age) {
            return -1;
        } else {
            return 0;
        }
    }
}
