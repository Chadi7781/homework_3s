package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.EmployeRepository;

import java.util.Optional;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;


	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Optional<Employe> employe = employeRepository.findById(employeId);
		if(employe.isPresent()) {
			
			employe.get().setEmail(email);
			employeRepository.save(employe.get());
			//don't give up man 
		}
	}



	public String getEmployePrenomById(int employeId) {
		Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
		if(employeManagedEntity.isPresent()) {
			return employeManagedEntity.get().getPrenom();
		} else {
			return null;
		}
		
	}
	public void deleteEmployeById(int employeId)
	{
		Optional<Employe> employe = employeRepository.findById(employeId);

				if(employe.isPresent()) {
				employeRepository.delete(employe.get());
			
			
		}
	}


	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}
	
	

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}



	public List<Employe> getAllEmployes() {
				return (List<Employe>) employeRepository.findAll();
	}

}