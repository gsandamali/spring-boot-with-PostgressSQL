package com.application.controller.student;

import javax.validation.Valid;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.dto.enums.student.Status;
import com.application.entity.student.StudentEntity;
import com.application.service.student.StudentService;

@RestController
@RequestMapping("/students")
public class LogInRegisterController {

    private final StudentService studentService;

    public LogInRegisterController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@Validated @RequestBody StudentEntity newStudent) {
        try {
            return ResponseEntity.ok(studentService.isStudentAvailable(newStudent));  // return 200, with json body
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Status> loginStudent(@Valid @RequestBody StudentEntity student) {
        try {
            return ResponseEntity.ok(studentService.studentLoggingState(student, true));  // return 200, with json body
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutStudent(@Valid @RequestBody StudentEntity student) {
        try {
            return ResponseEntity.ok(studentService.studentLoggingState(student, false));  // return 200, with json body
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }
}
