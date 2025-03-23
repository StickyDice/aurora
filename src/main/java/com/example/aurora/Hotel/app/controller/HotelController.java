package com.example.aurora.Hotel.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/hotel")
@RequiredArgsConstructor
public class HotelController {
  @GetMapping()
  public String hello() {
    return "hello world";
  }

  @GetMapping("/{id}")
  public ResponseEntity<String> getHotelById(@PathVariable Integer id) {
    // return ResponseEntity.notFound().build();
    return ResponseEntity.ok("success");
  }
}
