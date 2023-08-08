package edu.pnu.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Map<String, Set<String>>> getAllTags() {
        Map<String, Set<String>> tagMaps = new HashMap<>();

        Set<String> placeTags = new HashSet<>(busanPlaceRepository.findAllTags());
        tagMaps.put("placeTags", placeTags);

        Set<String> festivalTags = new HashSet<>(busanFestivalRepository.findAllTags());
        tagMaps.put("festivalTags", festivalTags);

        Set<String> tourTags = new HashSet<>(busanTourRepository.findAllTags());
        tagMaps.put("tourTags", tourTags);

        return ResponseEntity.ok(tagMaps);
    }

    
//    @GetMapping("/alltags")
//    public ResponseEntity<Map<String, TagsDTO>> getAllTags() {
//        Map<String, TagsDTO> tagMaps = new HashMap<>();
//
//        TagsDTO placeTagsDTO = new TagsDTO();
//        placeTagsDTO.setTags(busanPlaceRepository.findAllTags());
//        tagMaps.put("placeTags", placeTagsDTO);
//
//        TagsDTO festivalTagsDTO = new TagsDTO();
//        festivalTagsDTO.setTags(busanFestivalRepository.findAllTags());
//        tagMaps.put("festivalTags", festivalTagsDTO);
//
//        TagsDTO tourTagsDTO = new TagsDTO();
//        tourTagsDTO.setTags(busanTourRepository.findAllTags());
//        tagMaps.put("tourTags", tourTagsDTO);
//
//        return ResponseEntity.ok(tagMaps);
//    }


//    @GetMapping("/alltags")
//    public ResponseEntity<TagsDTO> getAllTags() {
//        TagsDTO dto = new TagsDTO();
//        dto.setTags(busanPlaceRepository.findAllTags());
//        dto.getTags().addAll(busanFestivalRepository.findAllTags());
//        dto.getTags().addAll(busanTourRepository.findAllTags());
//        return ResponseEntity.ok(dto);
//    }
}
