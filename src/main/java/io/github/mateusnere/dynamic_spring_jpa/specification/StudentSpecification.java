package io.github.mateusnere.dynamic_spring_jpa.specification;

import io.github.mateusnere.dynamic_spring_jpa.model.School;
import io.github.mateusnere.dynamic_spring_jpa.model.Student;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<Student> nameEndsWithIgnoreCase(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase());
    }

    public static Specification<Student> isAge(int age) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("age"), age);
    }

    public static Specification<Student> isSchoolBorough(String borough) {
        return (root, query, criteriaBuilder) -> {
            Join<Student, School> schoolJoin = root.join("school");
            return criteriaBuilder.equal(schoolJoin.get("borough"), borough);
        };
    }

    public static Specification<Student> isSchoolBoroughLike(String partOfBoroughName) {
        return (root, query, criteriaBuilder) -> {
            Join<Student, School> schoolJoin = root.join("school");
            return criteriaBuilder.like(criteriaBuilder.lower(schoolJoin.get("borough")), "%" + partOfBoroughName.toLowerCase() + "%");
        };
    }
}
