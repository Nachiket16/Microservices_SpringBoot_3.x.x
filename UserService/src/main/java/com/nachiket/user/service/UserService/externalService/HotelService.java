package com.nachiket.user.service.UserService.externalService;

import com.nachiket.user.service.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

  @GetMapping("/hotels/{hotelId}")
  Hotel getHotel(@PathVariable("hotelId")String hotelId);

}
