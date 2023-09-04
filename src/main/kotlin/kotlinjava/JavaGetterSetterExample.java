package kotlinjava;

import java.time.LocalDate;

public class JavaGetterSetterExample {

    public static void main(String[] args) {
        Student student = new Student();

        student.setName("name");
        student.setBirthDate(LocalDate.of(1999, 12, 1));

        System.out.println(student.getName());
        System.out.println(student.getBirthDate());
    }


}
