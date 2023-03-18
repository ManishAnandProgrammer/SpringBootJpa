package com.example.utils;

import com.example.domains.Student;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import java.util.concurrent.ThreadLocalRandom;

public class StudentUtils {
    private StudentUtils() {
        throw new UnsupportedOperationException("No Object For This Class..!");
    }

    public static Student getStudent() {
        Faker faker = Faker.instance();
        Name name = faker.name();
        int age = ThreadLocalRandom.current().nextInt(5, 50 + 1);

        Student student = new Student();
        student.setFirstName(name.firstName());
        student.setLastName(name.lastName());
        student.setEmail(name.firstName().toLowerCase() + "@gmail.com");
        student.setAge(age);

        return student;
    }
}
