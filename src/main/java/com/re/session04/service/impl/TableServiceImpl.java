package com.re.session04.service.impl;

import com.re.session04.model.RestaurantTable;
import com.re.session04.repository.TableRepository;
import com.re.session04.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;

    @Autowired
    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public List<RestaurantTable> getAll() {
        return tableRepository.findAll();
    }

    @Override
    public List<RestaurantTable> getByCapacity(int capacity) {
        return tableRepository.findByCapacity(capacity);
    }

    @Override
    public RestaurantTable getById(int id) {
        return tableRepository.findById(id);
    }

    @Override
    public void updateStatus(int id, String status) {
        tableRepository.updateStatus(id, status);
    }

    @Override
    public String deleteTable(int id) {
        RestaurantTable table = tableRepository.findById(id);
        if (table == null) {
            return "Bàn không tồn tại!";
        }
        if ("Đang dùng".equals(table.getStatus())) {
            return "Không thể xóa bàn đang có khách!";
        }
        tableRepository.deleteById(id);
        return null;
    }
}
