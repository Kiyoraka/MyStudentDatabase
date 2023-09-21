package com.example.MyStudentDatabaseV2.model;


import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;




@Data
@Table("student")

public class Student {

    @Column("ICNo")
    private String ICNo;

    @Column("name")
    private String Name;

    @Column("HpNo")
    private String HpNo;

    @Column("course")
    private String Course;

    public Student(String ICNo, String Name, String HpNo, String Course) {
        this.ICNo = ICNo;
        this.Name = Name;
        this.HpNo = HpNo;
        this.Course = Course;
    }

    //Return the data to the class that call them
    public Object getICNo() { return ICNo;}

    public String getName() {
        return Name;
    }
    public String getHpNo() {
        return HpNo;
    }
    public String getCourse() {
        return Course;
    }

}
