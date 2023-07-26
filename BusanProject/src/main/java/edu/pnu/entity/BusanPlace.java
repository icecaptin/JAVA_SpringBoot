package edu.pnu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "busan_place") // 테이블명을 명시적으로 지정
public class BusanPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uc_seq;
    private String name;
    private String gugun;
    
    private String lat;
    private String lng;
    
    private String travel_place;
    private String title;
    private String subtitle;
    private String addr1;
    private String homepage_u;
    private String trfc_info;
    private String usage_day;
    private String hldy_info;
    private String usage_time;
    private String usage_amou;
    private String middle_siz;
    private String main_img_n;
    private String main_img_t;
    private String itemcntnts;    
    private String geometry;
}
