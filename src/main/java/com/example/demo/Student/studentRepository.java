package com.example.demo.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface studentRepository extends JpaRepository<Student, Long> {
}
