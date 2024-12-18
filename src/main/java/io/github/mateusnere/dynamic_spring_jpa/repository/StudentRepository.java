package io.github.mateusnere.dynamic_spring_jpa.repository;

import io.github.mateusnere.dynamic_spring_jpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
}
