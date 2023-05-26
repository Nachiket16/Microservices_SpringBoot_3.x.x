package com.nachiket.user.service.UserService.controller;

import com.nachiket.user.service.UserService.entities.User;
import com.nachiket.user.service.UserService.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

  @Autowired
  private UserServiceImpl userService;


  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User user1 = userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(user1);
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUSer() {
    List<User> allUser = userService.getAllUser();
    return ResponseEntity.ok(allUser);
  }

  @GetMapping("/{userId}")
  @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
  public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
    User user = userService.getUser(userId);
    return ResponseEntity.ok(user);
  }

  public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
//    log.info("Fallback is executed because service is down : ",ex.getMessage());
    User user = User.builder()
        .email("dummy@gmail.com")
        .name("Dummy dum dum")
        .about("This is created dummy because some service is down")
        .userId("1234")
        .build();
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

}
