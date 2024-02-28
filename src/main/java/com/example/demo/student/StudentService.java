package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service

public class StudentService {
    private StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


        public List<Student> getStudent(){
          return studentRepository.findAll();
        }

    @Override
    public String toString() {
        return "StudentService{" +
                "studentRepository=" + studentRepository +
                '}';
    }


    public void addNewStudent( Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
            studentRepository.save(student);
        }
    public void deleteStudent(Long studentId){
      Optional<Student> exists =  studentRepository.findById(studentId);
        if (exists.isEmpty()){
            throw new IllegalStateException("student with id " + studentId + "doesnt exists");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email){
        Student student =  studentRepository.findById(studentId)

                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + "doesnt exists"));

                if (name != null && !name.isEmpty() &&
        !Objects.equals(student.getName(), name)){
           student.setName(name);
        }

        if (email != null && !email.isEmpty() &&
                !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional =  studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                   throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }

    private void orElseThrow() {
    }
}

