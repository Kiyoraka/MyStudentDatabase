package com.example.MyStudentDatabaseV2.repository;

import com.example.MyStudentDatabaseV2.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    // Custom query method to prevent some error related to sql statement

    @Modifying
    @Query("INSERT INTO student (ICNo, Name, HpNo, Course) VALUES (:ICNo, :Name, :HpNo, :Course)")
    void SaveStudent(
            @Param("ICNo") String ICNo,
            @Param("Name") String Name,
            @Param("HpNo") String HpNo,
            @Param("Course") String Course);

    @Modifying
    @Query("DELETE FROM student WHERE ICNo = :ICNo")
    void DeleteStudent(@Param("ICNo") String ICNo);

    @Modifying
    @Query("UPDATE student SET Name = :Name, HpNo = :HpNo, Course = :Course WHERE ICNo = :ICNo")
    void UpdateStudent(
            @Param("ICNo") String ICNo,
            @Param("Name") String Name,
            @Param("HpNo") String HpNo,
            @Param("Course") String Course);

    @Query("SELECT * FROM student WHERE ICNo = :ICNo")
    List<Student> findByICNo(@Param("ICNo") String ICNo);
}
