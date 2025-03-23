package com.example.aurora.Hotel.app.IUsecase;

import java.util.Optional;

import com.example.aurora.Hotel.domain.model.Hotel;

public interface IHotelUsecase {
  public Optional<Hotel> getHotelById(Integer id);
}
