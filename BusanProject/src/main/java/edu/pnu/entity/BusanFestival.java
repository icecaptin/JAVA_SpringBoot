package edu.pnu.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "busanfestival")
public class BusanFestival {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String gugun;
	private String name;
	private String subname;
	private String type;

	@Column(name = "start_date")
	private LocalDate startDate;
	private String end_date;
	private String place;
	private String hosting_method;
	private String tags;
	private String main_img;

	// 'start_date' 프로퍼티의 Getter와 Setter
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

}