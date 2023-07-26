package edu.pnu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "busan_festival") // 테이블명을 명시적으로 지정
public class BusanFestival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uc_seq;
    private String city;
    private String gugun;
    private String name_festival;
    private String subname_festival;
    private String festival_type;
    private String festival_date;
    private String festival_place;
    private String hosting_method;
    private String tags;
    private String first_held_year;
}
