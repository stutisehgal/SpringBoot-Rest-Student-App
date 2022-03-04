package com.trg.boot.controllers;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trg.model.AppRes;
import com.trg.model.Student;

@RestController //creating a controller class with this annotation
@RequestMapping("/student") // controller class request mapping for servlet URLs
public class StudentController {

	Map<Integer,Student> data;
	
	public StudentController(){
		
		// data flows into the MVC Spring application from the Controller class and gets displayed by JSP
		data = new TreeMap<Integer,Student>();
		
		String strS = "2000-09-22";
		Date dateS = Date.valueOf(strS);
		
		String strN = "2009-07-18";
		Date dateN = Date.valueOf(strN);
		
		String strV = "1973-12-12";
		Date dateV = Date.valueOf(strV);
		
		String strP = "1970-08-20";
		Date dateP = Date.valueOf(strP);
		
		data.put(100, new Student(100,"Stuti",96,dateS));
		data.put(200, new Student(200,"Neeti",98,dateN));
		data.put(300, new Student(300,"Vani",99,dateV));
		data.put(400, new Student(400,"Pankaj",95,dateP));
	}
	
	//@RequestMapping(value= "{sid}", method = RequestMethod.GET) // so that URL sid = 100 will automatically enter here
	@GetMapping("{sid}")
	public ResponseEntity<?> getStudent(@PathVariable("sid") int id)
	{
		Student s = data.get(id);
		//return s;
		if(s==null) {
			return new ResponseEntity<AppRes>(new AppRes("SAVEFAILED", "Student cannot be saved" + s),HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Student> ( s,HttpStatus.ACCEPTED);
		}
	}
	
	@RequestMapping
	public Collection<Student> getAllStudent() //REQUEST
	{
		Collection<Student> col = data.values();
		return col;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String saveStudent(@RequestBody Student s) //UPDATE
		{
		if(data.containsKey(s.getId())){
			//return "Student id"+s.getId()+"already Exists";
			return "Student with id"+s.getId()+"successfully updated";
			
		}
		else {
			return "Employee data with id"+s.getId()+"not found";
			}
		
			}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> updateStudent(@RequestBody Student s){ //UPDATE response
		if(data.containsKey(s.getId()))
	{
			data.put(s.getId(), s);
			return new ResponseEntity<String>("Updated",HttpStatus.ACCEPTED);
	}
		else {
			return new ResponseEntity<String>("Does not exist",HttpStatus.CONFLICT);
			}

	}
	
	
	@RequestMapping(value= "{sid}", method = RequestMethod.DELETE)
	public String deleteStudent(@PathVariable("sid") int id) //DELETE
	{
		int st = id;
		if(data.containsKey(st)){
			data.remove(st);
			return "Done "+ st;
			}
		else{
			return "Not done";
			}

	}
	
	@RequestMapping("id={sid}/name={name}/marks={marks}/dob={dob}")
	public String saveEmp(@PathVariable("sid") int id,@PathVariable("name") String name,@PathVariable("marks") float marks, @PathVariable("dob") Date dob)
	{ //CREATE
		if(data.containsKey(id)){
			return "Student Already Exists";
	}
		else {
			data.put(id, new Student(id,name,marks,dob));
			return "Student Saved";
	}
	
}
	

}

