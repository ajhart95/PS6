package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-12-27");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Alex");
		person1.setMiddleName("J");
		person1.setLastName("Heart");
		person1.setBirthday(person1Birth);
		person1.setCity("Collegeville");
		person1.setStreet("702 Stones Gate Blvd");
		person1.setPostalCode(21921);
		
	}
	@After
	public void tearDown() throws Exception {
		// this delete the person we test after each test
		PersonDomainModel pers;
		
		PersonDAL.deletePerson(person1.getPersonID());
		pers = PersonDAL.getPerson(person1.getPersonID());
		assertNull(pers);
	}
	
	@Test
	public void TestAddPerson(){
		PersonDomainModel per;

		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNotNull(per);
		}
	
	@Test
	public void TestUpdatePerson(){
		PersonDomainModel per;
		
		PersonDAL.addPerson(person1);
		
		person1.setFirstName("TestName");
		PersonDAL.updatePerson(person1);
		
		per = PersonDAL.getPerson(person1.getPersonID());
		assertEquals(person1.getFirstName(), "TestName");
		
	}
	
	@Test
	public void TestDeletePerson(){
		PersonDomainModel per;
		
		PersonDAL.addPerson(person1);
		
		PersonDAL.deletePerson(person1.getPersonID());
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull(per);
	}
	

}
