package com.job.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.app.Model.Job;
import com.job.app.Model.Service.JobServiceImpl;

@RestController
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	private JobServiceImpl jobService;
	
	@GetMapping("/")
	public String testController() {
		return "It's working";
	}
	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> showAllJobs(){
		List<Job> jobs = jobService.getAllJobs();
		return new ResponseEntity<>(jobs,HttpStatus.OK);
	}
	@GetMapping("/jobs/{id}")
	public ResponseEntity<Optional<Job>> getJobById(@PathVariable Long id){
		Optional<Job> job = jobService.getJobById(id);
		return new ResponseEntity<>(job,HttpStatus.OK);
		
	}
	@PostMapping("/jobs")
	public ResponseEntity<String> addJob(@RequestBody Job job){
		if(job.getJob_id()==null) {
		String message=jobService.addJob(job);
		return new ResponseEntity<>(message,HttpStatus.CREATED);
		}
		return new ResponseEntity("ID Should not be entered",HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id){
		String message = jobService.deleteJob(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}
	@PutMapping("/jobs/{id}")
	public ResponseEntity<String> updateJob(@PathVariable("id") Long id, @RequestBody Job job){
		String message = jobService.updateJob(id,job);
		return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
		
	}

}
