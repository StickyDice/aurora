package com.example.aurora.Hotel.data.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.aurora.Hotel.domain.model.Hotel;

@Repository
public interface HotelRepo extends CrudRepository<Hotel, Integer> {

}
