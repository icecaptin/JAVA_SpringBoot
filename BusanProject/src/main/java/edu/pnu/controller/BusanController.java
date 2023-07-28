package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.entity.BusanPlace;
import edu.pnu.repository.BusanPlaceRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 클라이언트의 도메인과 포트를 여기에 입력
public class BusanController {

    private final BusanPlaceRepository busanPlaceRepository;

    public BusanController(BusanPlaceRepository busanPlaceRepository) {
        this.busanPlaceRepository = busanPlaceRepository;
    }

    @GetMapping("/busanplace")
    public List<BusanPlace> getBusanPlaces() {
        return busanPlaceRepository.findAll();
    }
} 
