package com.nachiket.hotel.controller;

import com.nachiket.hotel.entities.Hotel;
import com.nachiket.hotel.service.HotelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hotels")
@RestController
public class HotelController {

  @Autowired
  private HotelService hotelService;

  @PreAuthorize("hasAuthority('Admin')")
  @PostMapping
  public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
    return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
  }

  @PreAuthorize("hasAuthority('SCOPE_internal')")
  @GetMapping("/{hotelId}")
  public ResponseEntity<Hotel> getById(@PathVariable String hotelId) {
    Hotel hotel = hotelService.get(hotelId);
    return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
  }

  @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
  @GetMapping()
  public ResponseEntity<List<Hotel>> getAll() {
    return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.getAll());
  }

}
