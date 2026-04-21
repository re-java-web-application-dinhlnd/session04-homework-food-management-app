package com.re.session04.repository.impl;

import com.re.session04.model.RestaurantTable;
import com.re.session04.repository.TableRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TableRepositoryImpl implements TableRepository {
    private final List<RestaurantTable> tables;

    public TableRepositoryImpl() {
        this.tables = new ArrayList<>();
        tables.add(new RestaurantTable(1, 2, "Còn trống"));
        tables.add(new RestaurantTable(2, 4, "Đang dùng"));
        tables.add(new RestaurantTable(3, 4, "Còn trống"));
        tables.add(new RestaurantTable(4, 6, "Đang dùng"));
        tables.add(new RestaurantTable(5, 8, "Còn trống"));
    }

    @Override
    public List<RestaurantTable> findAll() {
        return tables;
    }

    @Override
    public RestaurantTable findById(int id) {
        return tables.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<RestaurantTable> findByCapacity(int capacity) {
        return tables.stream()
                .filter(t -> t.getCapacity() == capacity)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(int id, String status) {
        RestaurantTable table = findById(id);
        if (table != null) {
            table.setStatus(status);
        }
    }

    @Override
    public void deleteById(int id) {
        tables.removeIf(t -> t.getId() == id);
    }
}
