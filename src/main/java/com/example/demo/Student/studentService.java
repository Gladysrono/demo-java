package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class studentService {

    private final studentRepository studentRepository;

    @Autowired
    public studentService(studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        try {
            return studentRepository.findAll();
        } catch (Exception ex) {
            // Log the exception or rethrow as a custom exception
            throw new RuntimeException("Failed to fetch students", ex);
        }
    }

    public Optional<Student> getStudentById(Long id) {
        try {
            return studentRepository.findById(id);
        } catch (Exception ex) {
            // Log the exception or rethrow as a custom exception
            throw new RuntimeException("Failed to fetch student with id " + id, ex);
        }
    }

    public Student addStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (Exception ex) {
            // Log the exception or rethrow as a custom exception
            throw new RuntimeException("Failed to add student", ex);
        }
    }

    public void deleteStudent(Long id) {
        try {
            boolean exists = studentRepository.existsById(id);
            if (!exists) {
                throw new IllegalStateException("Student with id " + id + " does not exist");
            }
            studentRepository.deleteById(id);
        } catch (Exception ex) {
            // Log the exception or rethrow as a custom exception
            throw new RuntimeException("Failed to delete student with id " + id, ex);
        }
    }

    public Student updateStudent(Long id, Student newStudent) {
        try {
            return studentRepository.findById(id)
                    .map(student -> {
                        student.setName(newStudent.getName());
                        student.setEmail(newStudent.getEmail());
                        student.setDob(newStudent.getDob());
                        student.setAge(newStudent.getAge());
                        return studentRepository.save(student);
                    })
                    .orElseThrow(() -> new IllegalStateException("Student with id " + id + " not found"));
        } catch (Exception ex) {
            // Log the exception or rethrow as a custom exception
            throw new RuntimeException("Failed to update student with id " + id, ex);
        }
    }
}
