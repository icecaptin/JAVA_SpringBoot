package edu.pnu.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "busancomments") // 테이블명 지정
public class BusanComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String foodcomment;
    private String placecomment;
    private String tourcomment;
    private String festivalcomment;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;
    
    @ManyToOne
    @JoinColumn(name = "food_id") // 실제 외래키 칼럼명에 맞게 수정
    private BusanFood food;
    
    @ManyToOne
    @JoinColumn(name = "place_id") // 실제 외래키 칼럼명에 맞게 수정
    private BusanPlace place;
    
    public String getFoodcomment() {
        return foodcomment;
    }

    public void setFoodcomment(String foodcomment) {
        this.foodcomment = foodcomment;
    }
    
    public String getPlacecomment() {
        return placecomment;
    }

    public void setPlacecomment(String placecomment) {
        this.placecomment = placecomment;
    }
}
