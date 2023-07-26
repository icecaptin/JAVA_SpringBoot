package edu.pnu.service;

import edu.pnu.entity.BusanFood;
import edu.pnu.entity.BusanPlace;
import edu.pnu.entity.BusanTour;
import edu.pnu.entity.BusanFestival;
import edu.pnu.repository.BusanFoodRepository;
import edu.pnu.repository.BusanPlaceRepository;
import edu.pnu.repository.BusanTourRepository;
import edu.pnu.repository.BusanFestivalRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusanService {

    private final BusanFoodRepository busanFoodRepository;
    private final BusanFestivalRepository festivalRepository;
    private final BusanPlaceRepository busanPlaceRepository;
	private final BusanTourRepository busanTourRepository;

    public BusanService(BusanFestivalRepository festivalRepository, BusanFoodRepository busanFoodRepository,
    		BusanPlaceRepository busanPlaceRepository, BusanTourRepository busanTourRepository) {
        this.busanFoodRepository = busanFoodRepository;
        this.festivalRepository = festivalRepository;
        this.busanPlaceRepository = busanPlaceRepository;
        this.busanTourRepository = busanTourRepository;
    }
    
    public List<BusanFestival> getAllFestivals() {
        return festivalRepository.findAll();
    }

    // 특정 축제 데이터를 ID로 가져오는 메소드
    public BusanFestival getFestivalById(Long id) {
        return festivalRepository.findById(id).orElse(null);
    }

    // 모든 부산 음식 데이터를 가져오는 메소드
    public List<BusanFood> getAllBusanFood() {
        return busanFoodRepository.findAll();
    }

    // 특정 부산 음식 데이터를 ID로 가져오는 메소드
    public BusanFood getBusanFoodById(Long id) {
        return busanFoodRepository.findById(id).orElse(null);
    }
    
    public List<BusanPlace> getAllBusanPlace() {
        return busanPlaceRepository.findAll();
    }
    
    public BusanPlace getBusanPlaceById(Long id) {
        return busanPlaceRepository.findById(id).orElse(null);
    }
    
    public List<BusanTour> getAllBusanTour() {
        return busanTourRepository.findAll();
    }
    
    public BusanTour getBusanTourById(Long id) {
        return busanTourRepository.findById(id).orElse(null);
    }
}
