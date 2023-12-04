package com.job.app.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long company_id;
	
	@NotBlank(message="Company name cannnot be Blank")
	private String company_name;
	private String company_address;
	@NotNull(message="Company Phone is Required")
	private Long company_phone;
	
	@JsonIgnore
	@OneToMany(mappedBy="company", cascade=CascadeType.ALL)
	private List<Job> jobs;
	
	@OneToMany(mappedBy="company")
	private List<Reviews> reviews;
	
	public List<Reviews> getReviews() {
		return reviews;
	}
	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	public Long getCompany_id() {
		return company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public Long getCompany_phone() {
		return company_phone;
	}
	public void setCompany_phone(Long company_phone) {
		this.company_phone = company_phone;
	}
	
}
