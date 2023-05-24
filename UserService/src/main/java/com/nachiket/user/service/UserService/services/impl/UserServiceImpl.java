package com.nachiket.user.service.UserService.services.impl;

import com.nachiket.user.service.UserService.entities.Hotel;
import com.nachiket.user.service.UserService.entities.Rating;
import com.nachiket.user.service.UserService.entities.User;
import com.nachiket.user.service.UserService.exception.ResourceNotFoundException;
import com.nachiket.user.service.UserService.externalService.HotelService;
import com.nachiket.user.service.UserService.repository.UserRepository;
import com.nachiket.user.service.UserService.services.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HotelService hotelService;

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
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User "
            + "with given id does not exists on the server"));

    // Get ratings for the User
    Rating[] ratings = restTemplate.getForObject(
        "http://RATING-SERVICE/ratings/users/" + user.getUserId(),
        Rating[].class);
    log.info("List --> {}",ratings);

    List<Rating> ratingsOfUser = Arrays.stream(ratings).toList();

    List<Rating> ratingList = ratingsOfUser.stream().map(rating -> {
//      ResponseEntity<Hotel> forEntity =
//          restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(),
//              Hotel.class);
//      Hotel hotel = forEntity.getBody();
      Hotel hotel = hotelService.getHotel(rating.getHotelId());

      rating.setHotel(hotel);
      return rating;
    }).collect(Collectors.toList());

    user.setRatings(ratingList);

    return user;
  }
}
