package com.aurora.aurora.controller.Property;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurora.aurora.dto.property.CreatePropertyDto;
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

  @GetMapping("/{id}")
  public ResponseEntity<PropertyModel> property(@PathVariable Integer id) {
    return ResponseEntity.ok(propertyService.getPropertyById(id));
  }

  @PostMapping
  public void createProperty(@RequestBody CreatePropertyDto property) {
    propertyService.createProperty(property);
  }
}
