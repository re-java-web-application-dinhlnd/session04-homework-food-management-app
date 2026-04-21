package com.re.session04.repository;

import com.re.session04.model.RestaurantTable;

import java.util.List;

public interface TableRepository {
    List<RestaurantTable> findAll();
    RestaurantTable findById(int id);
    List<RestaurantTable> findByCapacity(int capacity);
    void updateStatus(int id, String status);
    void deleteById(int id);
}
