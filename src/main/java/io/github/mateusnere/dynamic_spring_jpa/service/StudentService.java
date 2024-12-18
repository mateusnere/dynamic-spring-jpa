package io.github.mateusnere.dynamic_spring_jpa.service;

import io.github.mateusnere.dynamic_spring_jpa.model.Student;
import io.github.mateusnere.dynamic_spring_jpa.repository.StudentRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentNameEndingWith(String name) {
        Student studentExample = new Student();
        studentExample.setName(name);
        ExampleMatcher customExampleMatcher =
                ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.ENDING).ignoreCase());
        Example<Student> example = Example.of(studentExample, customExampleMatcher);

        return studentRepository.findAll(example);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(new Student());
    }
}
