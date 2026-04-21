package com.re.session04.controller;

import com.re.session04.model.RestaurantTable;
import com.re.session04.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/tables")
public class TableController {
    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/list")
    public String listTables(@RequestParam(value = "cap", required = false) Integer cap, ModelMap modelMap) {
        List<RestaurantTable> tables;

        if (cap != null) {
            if (cap < 0) {
                modelMap.addAttribute("error", "Sức chứa không được là số âm!");
                tables = tableService.getAll();
            } else {
                tables = tableService.getByCapacity(cap);
            }
            modelMap.addAttribute("capFilter", cap);
        } else {
            tables = tableService.getAll();
        }

        modelMap.addAttribute("tables", tables);
        return "table-list";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewTable(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("table-detail");
        RestaurantTable table = tableService.getById(id);
        if (table == null) {
            mav.addObject("error", "Không tìm thấy bàn với ID: " + id);
        } else {
            mav.addObject("table", table);
        }
        return mav;
    }

    @PostMapping("/update-status")
    public String updateStatus(@RequestParam("id") int id, @RequestParam("status") String status) {
        tableService.updateStatus(id, status);
        return "redirect:/tables/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteTable(@PathVariable("id") int id, ModelMap modelMap) {
        String error = tableService.deleteTable(id);
        if (error != null) {
            modelMap.addAttribute("error", error);
            modelMap.addAttribute("tables", tableService.getAll());
            return "table-list";
        }
        return "redirect:/tables/list";
    }

    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat() {
        ModelAndView mav = new ModelAndView("table-detail");
        mav.addObject("error", "ID bàn phải là số nguyên!");
        return mav;
    }
}
