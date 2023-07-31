package edu.pnu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "busanfood")
public class BusanFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gugun;
    private String food_category;
    private String lat;
    private String lng;
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
    private String geometry;    
    
}
