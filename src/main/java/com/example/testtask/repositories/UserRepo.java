package com.example.testtask.repositories;

import com.example.testtask.models.entities.User;
import com.example.testtask.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findAllByStatus(Status status);
}
