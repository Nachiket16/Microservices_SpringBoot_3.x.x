package com.nachiket.user.service.UserService.services.impl;

import com.nachiket.user.service.UserService.entities.User;
import com.nachiket.user.service.UserService.exception.ResourceNotFoundException;
import com.nachiket.user.service.UserService.repository.UserRepository;
import com.nachiket.user.service.UserService.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  @Override
  public User getUser(String userId) {
    return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User "
        + "with given id does not exists"));
  }
}
