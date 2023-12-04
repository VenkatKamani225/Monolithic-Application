package com.job.app.Model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job.app.Model.Company;
import com.job.app.Model.Job;
import com.job.app.Repo.CompanyRepo;
import com.job.app.Repo.JobRepo;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepo jobRepo;
	
	@Autowired
	private CompanyService companyService;
	
	public List<Job> getAllJobs() {
		return jobRepo.findAll();
	}

	public Optional<Job> getJobById(Long id) {
		// TODO Auto-generated method stub
		// jobRepo.findById(id).orElse(null) -- If you don't want to use Optional
		if(jobRepo.findById(id) != null) {
			return jobRepo.findById(id);
		}
		return null;
		
	}

	public String addJob(Job job) {
		// TODO Auto-generated method stub
		
		
			Company c=job.getCompany();
			if(companyService.getCompanyById(c.getCompany_id())==null) {
				companyService.saveCompany(job.getCompany());
				jobRepo.save(job);
				return "Job & company Saved successfully";
			}else {
				jobRepo.save(job);
				return "Job saved SuccessFully";
			}
			
		
	}

	public String deleteJob(Long id) {
		// TODO Auto-generated method stub
		if(jobRepo.findById(id)==null) {
			return "JOb Not Found";
		}
		jobRepo.deleteById(id);
		return "Deleted SuccessFully";
	}


	public String updateJob(Long id, Job job) {
		// TODO Auto-generated method stub
		Optional<Job> j=jobRepo.findById(id);
		if(j.isPresent()) {
			Job oldJob=j.get();
			oldJob.setJob_title(job.getJob_title());
			oldJob.setJob_description(job.getJob_description());
			oldJob.setLocation(job.getLocation());
			oldJob.setMax_salary(job.getMax_salary());
			oldJob.setMin_salary(job.getMin_salary());
			oldJob.setCompany(job.getCompany());
			jobRepo.save(oldJob);
			return "Updated";
		}
		return "Not Found";
	}
	
	//@Transactional 
	public void deleteByCompany(Company company) {
		// TODO Auto-generated method stub
		jobRepo.deleteByCompany(company);
		
	}

}
