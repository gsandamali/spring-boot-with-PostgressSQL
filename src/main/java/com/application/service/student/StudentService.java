package com.application.service.student;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.dto.enums.student.Status;
import com.application.entity.student.StudentEntity;
import com.application.repository.StudentRepository;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentEntity saveStudent(StudentEntity student) {
        return this.studentRepository.save(student);
    }

    public List<StudentEntity> getStudentList() {
        return studentRepository.findAll();
    }

    public StudentEntity getStudentById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<StudentEntity> getStudentByUsername(String firstName) {
        return studentRepository.findByFirstName(firstName).orElse(null);
    }

    public List<StudentEntity> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email).orElse(null);
    }

    public List<StudentEntity> getPage(Pageable paging) {
        Page<StudentEntity> pages = studentRepository.findAll(paging);
        return pages.getContent();
    }

    public Status isStudentAvailable(StudentEntity newStudent){
        List<StudentEntity> students = this.getStudentList();
        System.out.println("New user: " + newStudent.toString());
        for (StudentEntity student : students) {
            System.out.println("Registered user: " + newStudent.toString());
            if (student.equals(newStudent)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        this.saveStudent(newStudent);
        return Status.USER_SUCCESSFULLY_REGISTERED;
    }

    public Status studentLoggingState(StudentEntity student, boolean loggingStatus){
        List<StudentEntity> students = this.getStudentList();
        for (StudentEntity currentStudent :students) {
            if (currentStudent.equals(student)) {
                currentStudent.setLoggedIn(loggingStatus);
                this.saveStudent(currentStudent);
                if(loggingStatus == true){
                    return Status.USER_SUCCESSFULLY_LOGIN;
                }else{
                    return Status.USER_SUCCESSFULLY_LOGOUT;
                }
            }
        }
        return Status.FAILURE;
    }
    
}
