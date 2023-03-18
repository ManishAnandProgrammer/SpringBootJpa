package com.example.services;

import com.example.domains.Student;
import com.example.respositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(String id) {
        return studentRepository.findById(Long.parseLong(id)).orElseThrow();
    }
}
