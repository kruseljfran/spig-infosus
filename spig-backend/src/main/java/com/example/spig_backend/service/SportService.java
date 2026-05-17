package com.example.spig_backend.service;

import com.example.spig_backend.model.Sport;
import com.example.spig_backend.repository.SportRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SportService {
    private final SportRepository sportRepository;

    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    public Sport createSport(Sport sport) {
        return sportRepository.save(sport);
    }

    public Sport updateSport(Long id, Sport sportDetails) {
        Sport sport = sportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sport nije pronađen"));
        sport.setNaziv(sportDetails.getNaziv());
        return sportRepository.save(sport);
    }

    public void deleteSport(Long id) {
        sportRepository.deleteById(id);
    }
}
