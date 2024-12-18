package io.github.mateusnere.dynamic_spring_jpa.specification;

import io.github.mateusnere.dynamic_spring_jpa.model.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<Student> nameEndsWithIgnoreCase(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase());
    }

    public static Specification<Student> isAge(int age) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("age"), age);
    }
}
