package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dto.TagsDTO;
import edu.pnu.repository.BusanFestivalRepository;
import edu.pnu.repository.BusanPlaceRepository;
import edu.pnu.repository.BusanTourRepository;

@RestController
@RequestMapping("/api")
public class TagsController {

    @Autowired
    private BusanPlaceRepository busanPlaceRepository;
    
    @Autowired
    private BusanFestivalRepository busanFestivalRepository;
    
    @Autowired
    private BusanTourRepository busanTourRepository;

    @GetMapping("/alltags")
    public ResponseEntity<TagsDTO> getAllTags() {
        TagsDTO dto = new TagsDTO();
        dto.setTags(busanPlaceRepository.findAllTags());
        dto.getTags().addAll(busanFestivalRepository.findAllTags());
        dto.getTags().addAll(busanTourRepository.findAllTags());
        return ResponseEntity.ok(dto);
    }
}
