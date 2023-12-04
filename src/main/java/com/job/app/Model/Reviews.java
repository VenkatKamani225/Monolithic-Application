package com.job.app.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reviews {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long review_id;
	private String review_title;
	private String review_description;
	private double review_rating;
	
	@JsonIgnore
	@ManyToOne
	private Company company;

	public long getReview_id() {
		return review_id;
	}

	public String getReview_title() {
		return review_title;
	}

	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}

	public String getReview_description() {
		return review_description;
	}

	public void setReview_description(String review_description) {
		this.review_description = review_description;
	}

	public double getReview_rating() {
		return review_rating;
	}

	public void setReview_rating(double review_rating) {
		this.review_rating = review_rating;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
