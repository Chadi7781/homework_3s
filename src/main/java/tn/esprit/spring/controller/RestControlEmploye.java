package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.services.IEmployeService;



@RestController
public class RestControlEmploye {

	
	@Autowired
	IEmployeService iemployeservice;
	
	
	// http://localhost:8081/SpringMVC/servlet/ajouterEmployer
	//
	
	@PostMapping("/ajouterEmployer")
	@ResponseBody
	public Employe ajouterEmploye(@RequestBody Employe employe)
	{
		iemployeservice.ajouterEmploye(employe);
		return employe;
	}
	
	// Modifier email : http://localhost:8081/SpringMVC/servlet/modifyEmail/1/newemail
	@PutMapping(value = "/modifyEmail/{id}/{newemail}") 
	@ResponseBody
	public void mettreAjourEmailByEmployeId(@PathVariable("newemail") String email, @PathVariable("id") int employeId) {
		iemployeservice.mettreAjourEmailByEmployeId(email, employeId);
		
	}


	

	
   
   @GetMapping(value = "getEmployePrenomById/{idemp}")
   @ResponseBody

   public String getEmployePrenomById(@PathVariable("idemp")int employeId) {
		return iemployeservice.getEmployePrenomById(employeId);
	}

    @DeleteMapping("/deleteEmployeById/{idemp}") 
	@ResponseBody 
	public void deleteEmployeById(@PathVariable("idemp")int employeId) {
		iemployeservice.deleteEmployeById(employeId);
		
	}
    
    
    @GetMapping(value = "getNombreEmployeJPQL")
    @ResponseBody
	public int getNombreEmployeJPQL() {
		
		return iemployeservice.getNombreEmployeJPQL();
	}

    @GetMapping(value = "getAllEmployeNamesJPQL")
    @ResponseBody

	public List<String> getAllEmployeNamesJPQL() {
		
		return iemployeservice.getAllEmployeNamesJPQL();
	}


 	@PutMapping(value = "/mettreAjourEmailByEmployeIdJPQL/{id}/{newemail}") 
 	@ResponseBody
	public void mettreAjourEmailByEmployeIdJPQL(@PathVariable("newemail") String email, @PathVariable("id") int employeId) {	
	iemployeservice.mettreAjourEmailByEmployeIdJPQL(email, employeId);
		
	}

   



	@GetMapping(value = "/getAllEmployes")
    @ResponseBody

	public List<Employe> getAllEmployes() {
		
		return iemployeservice.getAllEmployes();
	}

	
	
}
