package io.github.mateusnere.dynamic_spring_jpa.controller;

import io.github.mateusnere.dynamic_spring_jpa.model.Student;
import io.github.mateusnere.dynamic_spring_jpa.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/endswith/{name}")
    public ResponseEntity<List<Student>> getStudentsNameEndingWith(@PathVariable("name") String name) {
        List<Student> studentNameEndingWith = studentService.getStudentNameEndingWith(name);
        return ResponseEntity.ok(studentNameEndingWith);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student studentById = studentService.getStudentById(id);
        return ResponseEntity.ok(studentById);
    }

    @GetMapping("/specific")
    public ResponseEntity<List<Student>> getSpecificsStudents() {
        var age = 20;
        var endsWith = "Smith";
        var schoolBorough = "Ealing";

        List<Student> specificsStudents = studentService.getSpecificsStudents(age, endsWith, schoolBorough);
        return ResponseEntity.ok(specificsStudents);
    }

    @GetMapping("/studyatborough/{borough}")
    public ResponseEntity<List<Student>> getStudentsByPartOfBorough(@PathVariable("borough") String partOfBoroughName) {
        List<Student> studentsByPartOfBoroughName = studentService.getStudentsByPartOfBoroughName(partOfBoroughName);
        return ResponseEntity.ok(studentsByPartOfBoroughName);
    }
}
