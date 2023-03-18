package com.example.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students", uniqueConstraints = {
    @UniqueConstraint(name = "student_unique_email", columnNames = "email")
})
@Getter
@Setter
public class Student extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(
        name = "email", nullable = false, columnDefinition = "TEXT"
    )
    private String email;
}
