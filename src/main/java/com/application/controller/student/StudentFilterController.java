package com.application.controller.student;

import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.service.student.StudentService;

@RestController
@RequestMapping("/students")
public class StudentFilterController {
    

    private final StudentService studentService;

    public StudentFilterController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> getStudent() {
        return ResponseEntity.ok(this.studentService.getStudentList());
    }

    @GetMapping(value = "/id/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@Valid @PathVariable long Id) {
        try {
            return ResponseEntity.ok(this.studentService.getStudentById(Id));  // return 200, with json body
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @GetMapping(value = "/firstName/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByUsername(@Valid @PathVariable String firstName) {
        try {
            return ResponseEntity.ok(this.studentService.getStudentByUsername(firstName));  // return 200, with json body
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByEmail(@Valid @PathVariable String email) {
        try {
            return ResponseEntity.ok(this.studentService.getStudentByEmail(email));  // return 200, with json body
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }
    
}
