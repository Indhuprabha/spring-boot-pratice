package com.example.JPAdemo.Service;

import com.example.JPAdemo.Model.Student;
import com.example.JPAdemo.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    @Autowired
    StudentRepo studentRepo;
    private String student;


    public List<Student> getAllStuds() {
       return studentRepo.findAll();
    }

    public void addStudent(Student student) {
        studentRepo.save(student);

    }

    public ResponseEntity<String> getStudentsByID(int rno) {
      
        if (student != null) {
            return (ResponseEntity) ResponseEntity.ok();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found with rno: " + rno);
        }

    }


    public void updateStud(Student student) {
        studentRepo.save(student);
    }

    public void deleteStudByRno(int rno) {
        studentRepo.deleteById(rno);
    }

    public void clearStud() {
        studentRepo.deleteAll();
    }


    public List<Student> getStudTechnology(String tech) {
        return studentRepo.findByTechnology(tech);

    }

    public void addAllStudent(List<Student> students) {
        studentRepo.saveAll(students);
    }


}
