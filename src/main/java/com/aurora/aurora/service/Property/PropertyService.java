package com.aurora.aurora.service.Property;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aurora.aurora.models.property.PropertyModel;
import com.aurora.aurora.repositories.PropertyRepository;

@Service
public class PropertyService {
  @Autowired
  private PropertyRepository propertyRepository;

  public List<PropertyModel> getAllProperties() {
    return propertyRepository.findAll();
  }

  public List<PropertyModel> getPaginatedProperties(int skip, int limit) {
    PageRequest pageable = PageRequest.of(skip, limit);

    Page<PropertyModel> propertyPage = propertyRepository.findAll(pageable);

    return propertyPage.getContent();
  }
}
