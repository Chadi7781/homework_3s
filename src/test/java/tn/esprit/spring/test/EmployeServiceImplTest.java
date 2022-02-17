package tn.esprit.spring.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.aop.TrackTime;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class EmployeServiceImplTest implements AbstractBaseTest {

	
	@Autowired
	EmployeServiceImpl employeServiceImpl1;

	@Autowired
	@Mock
	EmployeRepository employeRepo;

	
	private Employe employe1;
	
	private static String mail = "chaditroudi@gmail.com";


     @Test
     public void contextLoads() {
    		//Context Load init for test Methods

     }
     
     
 final  Logger log = Logger.getLogger(EmployeServiceImplTest.class);
    @Test
    @TrackTime(message = "testCreateEmployee ")
    public void testCreateEmployee() {
    
		Employe employe = new Employe("Zied", "aloulo", "zied@gmail.com", true, Role.ADMINISTRATEUR);

        assertNotNull(employeServiceImpl1.ajouterEmploye(employe));
      log.info("Employee added with success");
       
   
    }
    
    @Test
    @TrackTime(message = "mettreAjourEmailByEmployeId ")
	public void mettreAjourEmailByEmployeIdTest() {
		employeServiceImpl1.mettreAjourEmailByEmployeId(mail, employe1.getId());
		Optional<Employe> e = employeRepo.findById(employe1.getId());
		if (e.isPresent()) {
			assertThat(e.get().getEmail()).isEqualTo(mail);
		}
	}
  @Test
    @TrackTime(message = "testDeleteEmployeById ")
    public void testDeleteEmployeById () {
    	
    	Employe employee = new Employe();
        employee.setEmail("employedelted@gmail.com");
        employee.setNom("deletedEmpNom");
        employee.setPrenom("deletedEmpPrenom");
        employee.setRole(Role.INGENIEUR);
        employee.setActif(true);
        employeServiceImpl1.ajouterEmploye(employee);
       if(employeRepo.findById(employee.getId()).isPresent()) {
    	   employeServiceImpl1.deleteEmployeById(employee.getId());
    	   assertTrue(true);
 		  log.info("Employee deleted with success");

       }
       else {
    	   assertTrue(false);
 		  log.info("Delete : Employee not found  success");

       }

	}
    
    @Test
    @TrackTime(message = "testGetEmployeById ")
    public void testGetEmployeById() {
    	Employe employee = new Employe();
        employee.setId(5);
        
        assertEquals("Chadi", 
        		employeServiceImpl1.getEmployePrenomById(employe1.getId())
        		);
        log.info( "Employe Prenom : "+employeServiceImpl1.getEmployePrenomById(5));
    }

    @Test
    @TrackTime(message = "testGetNombreEmploye ")

    public void testGetNombreEmploye() {
		assertNotEquals(0, employeServiceImpl1.getNombreEmployeJPQL());

		log.info("number employee : "+employeServiceImpl1.getNombreEmployeJPQL());
    }

    @Test
    @TrackTime(message = "getSalaireByEmployeIdJPQLTest ")

    public void getSalaireByEmployeIdJPQLTest() {

       
		float salaire = employeServiceImpl1.getSalaireByEmployeIdJPQL(employe1.getId());
		log.info("getSalaireByEmployeIdJPQL == " + salaire);
		assertThat(salaire).isEqualTo(5000);
	}

    
    
    
   
	@Override
	@Before
	public void baseSetUp() {
		employe1 = new Employe("Troudi", "Chadi", "chadi@gmail.com", false, Role.INGENIEUR);		
		Employe employe2 = new Employe("Ali", "aloune", "ali@gmail.com", true, Role.ADMINISTRATEUR);
		Employe employe3 = new Employe("Foulen", "benFoulen", "foulen@gmail.com", true, Role.CHEF_DEPARTEMENT);
		Employe employe = new Employe("Foulen", "benFoulen", "foulen@gmail.com", true, Role.CHEF_DEPARTEMENT);

		
		List<Employe> employeList = new ArrayList<>();
		    employeList.add(employe);	
		    employeList.add(employe1);	

		    employeList.add(employe2);
		    employeList.add(employe3);
		    
	
		employeRepo.saveAll(employeList);
		
	}

	@Override
	@After
	public void baseTearDown() {
		employeRepo.deleteAll();
		
		
		
	}
 
}
