package com.nachiket.user.service.UserService.services;

import com.nachiket.user.service.UserService.entities.User;
import java.util.List;
import org.springframework.stereotype.Service;

public interface UserService {

  User saveUser(User user);
  List<User> getAllUser();
  User getUser(String userId);

}
