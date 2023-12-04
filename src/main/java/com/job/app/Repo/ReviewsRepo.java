package com.job.app.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.job.app.Model.Reviews;

@Repository
public interface ReviewsRepo extends JpaRepository<Reviews,Long> {
	
	@Query("SELECT r FROM Reviews r WHERE r.company.company_id=:companyId")
	List<Reviews> findByCompanyId(@Param("companyId") Long companyId);

}
