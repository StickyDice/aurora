package com.example.aurora.Hotel.domain.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aurora.Hotel.app.IUsecase.IHotelUsecase;
import com.example.aurora.Hotel.data.repo.HotelRepo;
import com.example.aurora.Hotel.domain.model.Hotel;

@Service
public class HotelUsecase implements IHotelUsecase {

  @Autowired
  private HotelRepo hotelRepo;

  public Optional<Hotel> getHotelById(Integer id) {
    return hotelRepo.findById(id);
  }
}
