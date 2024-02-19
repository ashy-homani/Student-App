package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@Service

public class StudentService {
    private StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


        public List<Student> getStudent(){
          return studentRepository.findall();
        }

    @Override
    public String toString() {
        return "StudentService{" +
                "studentRepository=" + studentRepository +
                '}';
    }


    public void addNewStudent( Student student) {
        Optional<Student> findStudentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        If (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
            studentRepository.save(student);
        }
    }

