package edu.pnu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.entity.BusanFestival;
import edu.pnu.entity.BusanFood;
import edu.pnu.entity.BusanPlace;
import edu.pnu.entity.BusanTour;
import edu.pnu.repository.BusanFestivalRepository;
import edu.pnu.repository.BusanFoodRepository;
import edu.pnu.repository.BusanPlaceRepository;
import edu.pnu.repository.BusanTourRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BusanController {

	private BusanPlaceRepository busanPlaceRepository;
	private BusanFoodRepository busanFoodRepository;
	private BusanTourRepository busanTourRepository;
	private BusanFestivalRepository busanFestivalRepository;

	@Autowired
	public BusanController(BusanPlaceRepository busanPlaceRepository, BusanFoodRepository busanFoodRepository,
			BusanTourRepository busanTourRepository, BusanFestivalRepository busanFestivalRepository) {
		this.busanPlaceRepository = busanPlaceRepository;
		this.busanFoodRepository = busanFoodRepository;
		this.busanTourRepository = busanTourRepository;
		this.busanFestivalRepository = busanFestivalRepository;
	}
	
	
	//일반 restapi

	@GetMapping("/busanplace")
	public List<BusanPlace> getBusanPlaces() {
		return busanPlaceRepository.findAll();
	}

	@GetMapping("/busanfood")
	public List<BusanFood> getBusanFoods() {
		return busanFoodRepository.findAll();
	}

	@GetMapping("/busantour")
	public List<BusanTour> getBusanTour() {
		return busanTourRepository.findAll();
	}

	@GetMapping("/busanfestival")
	public List<BusanFestival> getBusanFestival() {
		return busanFestivalRepository.findAll();
	}

	//곧 축제 열리는거 최대 4개

	@GetMapping("/busanfestival/upcoming")
	public List<BusanFestival> getUpcomingFestivals(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return busanFestivalRepository.findUpcomingFestivals(pageRequest);
	}

	
	//id값 입력시 불러오기
	
	
	@GetMapping("/busanfestival/{id}")
	public Optional<BusanFestival> getBusanFestivalById(@PathVariable Long id) {
		return busanFestivalRepository.findById(id);
	}

	@GetMapping("/busanplace/{id}")
	public Optional<BusanPlace> getBusanPlaceById(@PathVariable Long id) {
		return busanPlaceRepository.findById(id);
	}

	@GetMapping("/busanfood/{id}")
	public Optional<BusanFood> getBusanFoodById(@PathVariable Long id) {
		return busanFoodRepository.findById(id);
	}
	
	@GetMapping("/busantour/{id}")
	public Optional<BusanTour> getBusanTourById(@PathVariable Long id) {
		return busanTourRepository.findById(id);
	}
	
	
	//태그 탑20

	@GetMapping("/tourtagtop15")
	public List<Map<String, Object>> getTop20TourTags() {
		List<Object[]> top20Tags = busanTourRepository.findTop20TourTags();
		List<Map<String, Object>> result = new ArrayList<>();

		for (Object[] row : top20Tags) {
			String tagWord = (String) row[0];
			Long tagCount = (Long) row[1];

			Map<String, Object> tagMap = new HashMap<>();
			tagMap.put("tagWord", tagWord);
			tagMap.put("tagCount", tagCount);

			result.add(tagMap);
		}

		return result;
	}
	
	@GetMapping("/placetagtop15")
	public List<Map<String, Object>> getTop20PlaceTags() {
		List<Object[]> top20Tags = busanPlaceRepository.findTop20PlaceTags();
		List<Map<String, Object>> result = new ArrayList<>();

		for (Object[] row : top20Tags) {
			String tagWord = (String) row[0];
			Long tagCount = (Long) row[1];

			Map<String, Object> tagMap = new HashMap<>();
			tagMap.put("tagWord", tagWord);
			tagMap.put("tagCount", tagCount);

			result.add(tagMap);
		}

		return result;
	}
	
	@GetMapping("/festivaltagtop15")
	public List<Map<String, Object>> getTop20FestivalTags() {
		List<Object[]> top20Tags = busanFestivalRepository.findTop20FestivalTags();
		List<Map<String, Object>> result = new ArrayList<>();

		for (Object[] row : top20Tags) {
			String tagWord = (String) row[0];
			Long tagCount = (Long) row[1];

			Map<String, Object> tagMap = new HashMap<>();
			tagMap.put("tagWord", tagWord);
			tagMap.put("tagCount", tagCount);

			result.add(tagMap);
		}

		return result;
	}

}
