package com.example.MyStudentDatabaseV2.repository;

import com.example.MyStudentDatabaseV2.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    //Retrieved the data from the database
    @Query("SELECT * FROM user WHERE userid = :userId AND password = :password")
    User findByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);
}
