package com.example.MyStudentDatabaseV2.model;

import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

//Store the user data temporarily
@Data
@Table("`user`")
public class User {
    private String userId;
    private String password;
    public Object getPassword() {
        return null;
    }
}
