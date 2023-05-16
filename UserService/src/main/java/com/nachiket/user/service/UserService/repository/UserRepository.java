package com.nachiket.user.service.UserService.repository;

import com.nachiket.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
