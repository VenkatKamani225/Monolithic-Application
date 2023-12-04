package com.job.app.Model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.app.Model.Company;
import com.job.app.Model.Reviews;
import com.job.app.Repo.ReviewsRepo;

@Service
public class ReviewsService {
	
	@Autowired
	private ReviewsRepo reviewsRepo;
	
	@Autowired
	private CompanyService companyService;

	public List<Reviews> getAllReviews(Long companyId) {
		// TODO Auto-generated method stub
		List<Reviews> reviewsList = reviewsRepo.findByCompanyId(companyId);
		return reviewsList;
	}

	public boolean addReview(Long companyId, Reviews review) {
		// TODO Auto-generated method stub
		Company c=companyService.getCompanyById(companyId);
		if(c!=null) {
			review.setCompany(c);
			reviewsRepo.save(review);
			return true;
		}else {
		return false;
		}
	}

	public Reviews getReviewById(Long companyId, Long id) {
		// TODO Auto-generated method stub
		
		Company c= companyService.getCompanyById(companyId);
		if(c!=null){
			List<Reviews> rev = reviewsRepo.findByCompanyId(companyId);
			return rev.stream().filter(review->id.equals(review.getReview_id())).findFirst().orElse(null);
		}else {
			return null;
		}
	}

	public boolean deleteReview(Long companyId, Long id) {
		// TODO Auto-generated method stub
		Company c= companyService.getCompanyById(companyId);
		
		if(c!=null && reviewsRepo.findById(id)!=null) {
			reviewsRepo.deleteById(id);
			return true;
		}
			return false;
	}

}
