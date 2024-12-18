package io.github.mateusnere.dynamic_spring_jpa.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import io.github.mateusnere.dynamic_spring_jpa.model.QStudent;
import io.github.mateusnere.dynamic_spring_jpa.model.Student;
import io.github.mateusnere.dynamic_spring_jpa.repository.StudentRepository;
import io.github.mateusnere.dynamic_spring_jpa.specification.StudentSpecification;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
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

    public List<Student> getSpecificsStudents(int age, String endsWith, String borough) {
        Specification<Student> studentSpec =
                Specification
                        .where(StudentSpecification.nameEndsWithIgnoreCase(endsWith))
                        .and(StudentSpecification.isAge(age))
                        .and(StudentSpecification.isSchoolBorough(borough));

        return studentRepository.findAll(studentSpec);
    }

    public List<Student> getStudentsByPartOfBoroughName(String partOfBoroughName) {
        Specification<Student> studentSpec =
                Specification.where(StudentSpecification.isSchoolBoroughLike(partOfBoroughName));
        return studentRepository.findAll(studentSpec);
    }

    public List<Student> getStudentsUsingQueryDSL() {
        QStudent qStudent = QStudent.student;
        BooleanExpression predicate =
                qStudent.name.endsWithIgnoreCase("smith")
                        .and(qStudent.age.eq(20))
                        .and(qStudent.school.borough.contains("Ealing"));
        return (List<Student>) studentRepository.findAll(predicate);
    }

    public List<Student> getStudentsUsingQueryDSLSchoolName(String name) {
        QStudent qStudent = QStudent.student;
        BooleanExpression predicate =
                qStudent.school.name.containsIgnoreCase(name);
        return (List<Student>) studentRepository.findAll(predicate);
    }
}
