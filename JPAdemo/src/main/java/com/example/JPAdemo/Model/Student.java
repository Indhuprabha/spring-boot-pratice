package com.example.JPAdemo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Student {
  @Id
    private int rno;

    private String name;
    private String gender;
    private String technology;

  public Student() {
  }


}
