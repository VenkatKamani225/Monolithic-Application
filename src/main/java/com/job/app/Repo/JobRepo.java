package com.job.app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.app.Model.Company;
import com.job.app.Model.Job;

@Repository
public interface JobRepo extends JpaRepository<Job,Long> {

	void deleteByCompany(Company company);

}
