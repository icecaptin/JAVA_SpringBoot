package edu.pnu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "busan_food") // 테이블명을 명시적으로 지정
public class BusanFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ucSeq;
    private String name; //식당이름
    private String gugun; //식당 구군 구분
    
    private String lat;
    private String lng;
    
    private String site;
    private String title;
    private String subtitle;
    private String address1;
    private String address2;
    private String homepage_u;
    private String usage_day;
    private String menu;
    private String main_img_n;
    private String main_img_t;
    private String itemcntnts;
    
    private String geometry; //이것도...필요없을듯
}