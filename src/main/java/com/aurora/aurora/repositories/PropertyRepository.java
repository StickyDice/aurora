package com.aurora.aurora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurora.aurora.models.property.PropertyModel;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyModel, Integer> {

}
