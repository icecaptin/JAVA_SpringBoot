package edu.pnu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "busantour")
public class BusanTour {

    @Id
    private Integer id;

    private String name;
    private String gugun;
    private String category;
    private Double lat;
    private Double lng;
    private String plage;
    private String tags;
    private String title;
    private String subtitle;
    private String main_place;
    private String addr;
    private String trfc_info;
    private String usage_day;    
    private String hldy_info;
    private String usage_time;
    private String usage_amou;
    private String middle_siz;
    private String main_img_n;
    private String main_img_t;
    private Integer likecnt;
}
