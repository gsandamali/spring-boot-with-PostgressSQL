package com.application.controller.student;

// Importing required classes
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.service.student.StudentService;
 
@RestController
@RequestMapping("/students")
public class PagedRestController {
 
    //@controllerMapping - balanna
    @Autowired StudentService studentService;

    @GetMapping(value = "/page/{page}/size/{size}")
    public ResponseEntity<?> getPageByPageNoAndSize(@Validated @PathVariable int page, @Valid @PathVariable int size){
        try {
            Pageable paging = PageRequest.of(
            page, size, Sort.by("id").ascending());
            return ResponseEntity.ok(studentService.getPage(paging));  // return 200, with json body
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }
 
    @GetMapping(value = "/page/{page}/size/{size}/sort/{sortBy}")
    public ResponseEntity<?> getSortPageByPageNoAndSize(@Validated @PathVariable int page, @Valid @PathVariable int size, @PathVariable String... sortBy){
        try {
            Pageable paging = PageRequest.of(
            page, size, Sort.by(sortBy).ascending());
            return ResponseEntity.ok(studentService.getPage(paging));  // return 200, with json body
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }
}
