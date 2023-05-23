package com.nachiket.hotel.service.serviceImpl;

import com.nachiket.hotel.entities.Hotel;
import com.nachiket.hotel.exception.ResourceNotFoudException;
import com.nachiket.hotel.repository.HotelRepository;
import com.nachiket.hotel.service.HotelService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {

  @Autowired
  private HotelRepository hotelRepository;
  @Override
  public Hotel create(Hotel hotel) {
    hotel.setId(UUID.randomUUID().toString());
    return hotelRepository.save(hotel);
  }

  @Override
  public List<Hotel> getAll() {
    return hotelRepository.findAll();
  }

  @Override
  public Hotel get(String id) {
    return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoudException("Hotel "
        + "with given id not found !!!"));
  }
}
