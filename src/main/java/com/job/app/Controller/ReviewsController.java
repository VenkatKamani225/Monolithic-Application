package com.job.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.app.Model.Service.ReviewsService;
import com.job.app.Model.Reviews;

@RestController
@RequestMapping("/company/companies/{companyId}")
public class ReviewsController {

	@Autowired
	private ReviewsService reviewsService;
	
	
	@GetMapping("/")
	public String testReviews() {
		return "Reviews is working";
	}
	
	@GetMapping("/reviews")
	public ResponseEntity<List<Reviews>> getAllReviews(@PathVariable Long companyId){
		List<Reviews> reviews= reviewsService.getAllReviews(companyId);
		return new ResponseEntity<>(reviews,HttpStatus.FOUND);
		
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Reviews review){
		boolean booleanValue= reviewsService.addReview(companyId,review);
		if(booleanValue) {
			return new ResponseEntity<>("Successfully added Review",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Not added",HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/reviews/{id}")
	public ResponseEntity<Reviews> getReviewById(@PathVariable Long companyId, @PathVariable Long id){
		Reviews review= reviewsService.getReviewById(companyId,id);
		if(review!=null) {
			return new ResponseEntity<>(review, HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/reviews/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long id){
		boolean val= reviewsService.deleteReview(companyId,id);
		return val?  new ResponseEntity<>("Deleted",HttpStatus.FOUND): new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
		
	}
	
}
