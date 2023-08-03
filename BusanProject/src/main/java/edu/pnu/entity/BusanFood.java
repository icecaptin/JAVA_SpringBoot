package edu.pnu.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "busanfood")
public class BusanFood {


    @Id
    private Integer id;

    private String name;
    private String gugun;
    private String category;
    private Double lat;
    private Double lng;
    private String address1;
    private String url;
    private String usage_open;
    private String usage_close;
    private String usage_breaktime;
    private String menu;
    private String main_img_n;
    private String main_img_t;
    private String itemcntnts;    
    private String geometry;
    private String likecnt;
    
}
