package com.job.app.Model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.job.app.Model.Company;
import com.job.app.Model.Job;
import com.job.app.Repo.CompanyRepo;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepo companyRepo;

	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		List<Company> companies=companyRepo.findAll();
		return companies;
	}

	public Company getCompanyById(Long id) {
		// TODO Auto-generated method stub
		Company company = companyRepo.findById(id).orElse(null);
		return company;
	}

	public String saveCompany(Company company) {
		// TODO Auto-generated method stub
		
			if(company.getCompany_id()!=null) {
				return "Already Exists";
			}else {
				companyRepo.save(company);
				return "Saved Successfully";
			}
		
	}

	public String deleteCompany(Long id) {
		// TODO Auto-generated method stub
		
		companyRepo.deleteById(id);
		return "Deleted Succesfully";
	}

	public boolean updateCompany(Long id, Company company) {
		// TODO Auto-generated method stub
		Optional<Company> c= companyRepo.findById(id);
		if(c.isEmpty()) {
			companyRepo.save(company);
			return false;
		}else {
			Company compan =c.get();
			compan.setCompany_address(company.getCompany_address());
			compan.setCompany_name(company.getCompany_name());
			compan.setCompany_phone(company.getCompany_phone());
			compan.setJobs(company.getJobs());
			companyRepo.save(compan);
			return true;
		}
		
	}
	

}
