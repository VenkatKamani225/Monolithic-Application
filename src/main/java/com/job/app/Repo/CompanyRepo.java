package com.job.app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.app.Model.Company;


@Repository
public interface CompanyRepo extends JpaRepository<Company,Long>{

}
