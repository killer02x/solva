package solvademo.solva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solvademo.solva.entity.SpendingLimit;
import solvademo.solva.sevice.LimitService;

@RestController
@RequestMapping("/api/limits")
public class SpendingLimitController {

    @Autowired
    private LimitService limitService;

    @PostMapping
    public void setNewLimit(@RequestParam double amount) {
        limitService.setNewLimit(amount);
    }

    @GetMapping("/current")
    public SpendingLimit getCurrentLimit() {
        return limitService.getCurrentLimit();
    }
}
