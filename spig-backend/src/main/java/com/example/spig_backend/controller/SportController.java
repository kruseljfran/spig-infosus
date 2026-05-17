package com.example.spig_backend.controller;

import com.example.spig_backend.model.Sport;
import com.example.spig_backend.service.SportService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sportovi")
@CrossOrigin(origins = "*")
public class SportController {
    private final SportService sportService;

    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping
    public List<Sport> getAllSports() {
        return sportService.getAllSports();
    }

    @PostMapping
    public Sport createSport(@RequestBody Sport sport) {
        return sportService.createSport(sport);
    }

    @PutMapping("/{id}")
    public Sport updateSport(@PathVariable Long id, @RequestBody Sport sport) {
        return sportService.updateSport(id, sport);
    }

    @DeleteMapping("/{id}")
    public void deleteSport(@PathVariable Long id) {
        sportService.deleteSport(id);
    }
}
