package edu.pnu.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	private final BusanPlaceRepository busanPlaceRepository;
	private final BusanFoodRepository busanFoodRepository;
	private final BusanTourRepository busanTourRepository;
	private final BusanFestivalRepository busanFestivalRepository;

	@Autowired
	public BusanController(BusanPlaceRepository busanPlaceRepository, BusanFoodRepository busanFoodRepository,
			BusanTourRepository busanTourRepository, BusanFestivalRepository busanFestivalRepository) {
		this.busanPlaceRepository = busanPlaceRepository;
		this.busanFoodRepository = busanFoodRepository;
		this.busanTourRepository = busanTourRepository;
		this.busanFestivalRepository = busanFestivalRepository;
	}

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

	@GetMapping("/busanfestival/upcoming")
    public List<BusanFestival> getUpcomingFestivals(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return busanFestivalRepository.findUpcomingFestivals(pageRequest);
    }

	@GetMapping("/busantour/tags")
	public List<String> getDistinctTags() {
		return busanTourRepository.findDistinctTags();
	}

	@GetMapping("/busantour/bytags")
	public List<BusanTour> getBusanTourByTags(@RequestParam("tags") String tags) {
		List<String> tagList = Arrays.asList(tags.split(","));
		List<BusanTour> filteredTours = busanTourRepository.findByAllTagsIn(tagList, (long) tagList.size());
		return filteredTours;
	}
}
