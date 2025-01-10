package com.aurora.aurora.controller.Property;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurora.aurora.models.property.PropertyModel;
import com.aurora.aurora.service.Property.PropertyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/property")
@RequiredArgsConstructor
public class PropertyController {
  private final PropertyService propertyService;

  @GetMapping
  public ResponseEntity<List<PropertyModel>> property(@RequestParam(required = false) Integer skip,
      @RequestParam(required = false) Integer limit) {
    if (skip == null || limit == null)
      return ResponseEntity.ok(propertyService.getAllProperties());

    return ResponseEntity.ok(propertyService.getPaginatedProperties(skip, limit));

  }
}
