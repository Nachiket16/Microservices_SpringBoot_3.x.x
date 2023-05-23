package com.nachiket.user.service.UserService.services.impl;

import com.nachiket.user.service.UserService.entities.User;
import com.nachiket.user.service.UserService.exception.ResourceNotFoundException;
import com.nachiket.user.service.UserService.repository.UserRepository;
import com.nachiket.user.service.UserService.services.UserService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User saveUser(User user) {
    String userId = UUID.randomUUID().toString();
    user.setUserId(userId);
    return userRepository.save(user);
  }

  @Override
  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  @Override
  public User getUser(String userId) {
    return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User "
        + "with given id does not exists on the server"));
  }
}
