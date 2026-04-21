package com.re.session04.service;

import com.re.session04.model.RestaurantTable;

import java.util.List;

public interface TableService {
    List<RestaurantTable> getAll();
    List<RestaurantTable> getByCapacity(int capacity);
    RestaurantTable getById(int id);
    void updateStatus(int id, String status);
    String deleteTable(int id);
}
