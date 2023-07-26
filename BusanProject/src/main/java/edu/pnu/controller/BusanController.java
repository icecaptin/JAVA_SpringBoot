package edu.pnu.controller;

import edu.pnu.entity.BusanFood;
import edu.pnu.entity.BusanPlace;
import edu.pnu.entity.BusanTour;
import edu.pnu.entity.BusanFestival;
import edu.pnu.repository.BusanFoodRepository;
import edu.pnu.repository.BusanPlaceRepository;
import edu.pnu.repository.BusanTourRepository;
import edu.pnu.repository.BusanFestivalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BusanController {

	private final BusanFestivalRepository festivalRepository;
    private final BusanFoodRepository busanFoodRepository;
    private final BusanPlaceRepository busanPlaceRepository;
    private final BusanTourRepository busanTourRepository;

    public BusanController(BusanFestivalRepository festivalRepository, BusanFoodRepository busanFoodRepository, 
    		BusanPlaceRepository busanPlaceRepository, BusanTourRepository busanTourRepository) {
        this.festivalRepository = festivalRepository;
        this.busanFoodRepository = busanFoodRepository;
        this.busanPlaceRepository = busanPlaceRepository;
        this.busanTourRepository = busanTourRepository;
    }

    @GetMapping("/festivals")
    public List<BusanFestival> getFestivals() {
        return festivalRepository.findAll();
    }
    
    @GetMapping("/busanfood")
    public List<BusanFood> getBusanFood() {
        return busanFoodRepository.findAll();
    }
    
    @GetMapping("/busanplace")
    public List<BusanPlace> getBusanPlace() {
        return busanPlaceRepository.findAll();
    }
    
    @GetMapping("/busantour")
    public List<BusanTour> getBusanTour() {
        return busanTourRepository.findAll();
    }
}

