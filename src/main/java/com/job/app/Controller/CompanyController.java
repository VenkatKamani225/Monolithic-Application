package com.job.app.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.app.Model.Company;
import com.job.app.Model.Job;
import com.job.app.Model.Service.CompanyService;
import com.job.app.Model.Service.JobServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private JobServiceImpl jobService;
	
	@GetMapping("/")
	public String testCompanyAPI() {
		return "Company is Working";
	}
	
	@GetMapping("/companies")
	public ResponseEntity<List<Company>> getAllCompanies(){
		List<Company> companies= companyService.getAllCompanies();
		return new ResponseEntity<>(companies,HttpStatus.OK);
		
	}
	
	@GetMapping("/companies/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id){
		Company company = companyService.getCompanyById(id);
		return new ResponseEntity<>(company,HttpStatus.FOUND);
		
	}
	@PostMapping("/companies")
	public ResponseEntity<String> saveCompany(@RequestBody @Valid Company company, BindingResult results){
		if(results.hasErrors()) {
			return new ResponseEntity<String>("Validation failed: "+results.getAllErrors(),HttpStatus.BAD_REQUEST);
		}else {
		String message = companyService.saveCompany(company);
		return new ResponseEntity<>(message,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/companies/{id}")
	@Transactional //This helps in maintaining the whole completion of the service or rollback. This is used because it involves multiple transactions to be happened, one is to delete jobs and other is to delete company
	public ResponseEntity<String> deleteCompany(@PathVariable("id") Long id){
		Company c = companyService.getCompanyById(id);
		if(c==null) {
			return new ResponseEntity<String>("No Company by that ID",HttpStatus.BAD_REQUEST);
		}
		jobService.deleteByCompany(c);
		String message = companyService.deleteCompany(id);
		return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
		
	}
	@PutMapping("/companies/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable("id") Long id, @RequestBody Company company){
		boolean value = companyService.updateCompany(id,company);
		return (value)?new ResponseEntity<>("Updated", HttpStatus.ACCEPTED):new ResponseEntity<>("Created",HttpStatus.CREATED);
		
	}

}
