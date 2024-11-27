package com.example.ticketbookingsystem.repo;

import com.example.ticketbookingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailId(String emailId);

}
