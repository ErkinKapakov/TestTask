package com.example.testtask.models.entities;

import com.example.testtask.models.enums.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    @Id
            @GeneratedValue
    Long id;
    String name;
    String email;
    String imageUri;
    @Enumerated(EnumType.STRING)
    Status status;
}
