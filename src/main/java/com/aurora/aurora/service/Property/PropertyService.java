package com.aurora.aurora.service.Property;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.aurora.aurora.dto.property.CreatePropertyDto;
import com.aurora.aurora.models.property.PropertyModel;
import com.aurora.aurora.repositories.PropertyRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class PropertyService {
  @Autowired
  private PropertyRepository propertyRepository;

  private final int hotelStuffPrice = 4999;

  public List<PropertyModel> getAllProperties() {
    return propertyRepository.findAll();
  }

  public List<PropertyModel> getPaginatedProperties(int skip, int limit) {
    PageRequest pageable = PageRequest.of(skip, limit);

    Page<PropertyModel> propertyPage = propertyRepository.findAll(pageable);

    return propertyPage.getContent();
  }

  public PropertyModel createProperty(@NonNull CreatePropertyDto property) {
    PropertyModel newProperty = new PropertyModel();
    newProperty.setName(property.getName());
    newProperty.setImages(property.getImages());
    newProperty.setAddress(property.getAddress());
    newProperty.setTags(property.getTags());
    newProperty.setGuest_tags(property.getGuest_tags());
    newProperty.setPrice(property.getPrice());
    newProperty.setReservation_price(property.getPrice() + hotelStuffPrice);
    newProperty.setDescription_articles(property.getDescription_articles());

    return propertyRepository.save(newProperty);
  }
}
