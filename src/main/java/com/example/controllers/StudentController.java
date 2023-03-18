package com.example.controllers;

import com.example.domains.Student;
import com.example.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping
    public Student getStudent(@RequestParam String id) {
        return studentService.findById(id);
    }

}
