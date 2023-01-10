package com.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.entity.student.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<List<StudentEntity>> findByEmail(String email);
    Optional<List<StudentEntity>> findByFirstName(String firstName);
}
