package com.bridgephase.foo.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgephase.foo.model.jpa.Person;
import com.bridgephase.foo.model.repository.PersonRepository;

/**
 * This is a sample controller definition which shows both a <code>ResponseBody</code> response as well as a view
 * mapped response.
 * 
 * @author Jaime Garcia
 */
@Controller
public class HelloWorldController {

	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * This request mapping method will render a response of "Hello World".
	 * 
	 * @return A rendered response body of "Hello World"
	 */
	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	@ResponseBody
	public Person person(@RequestBody Person newPerson) {
		Person person = new Person(newPerson.getFirstName(), newPerson.getLastName());
		person = personRepository.save(person);
		return person;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Person> persons(@RequestParam(value="firstName", required=false) String firstName) {
		if (firstName == null) {
			return personRepository.findAll();
		} else {
			return personRepository.findByFirstName(firstName);
		}
	}
	
	/**
	 * This request mapping method will render a view. The views are
	 * stored under the <code>/src/main/resources/public</code> directory, the returned
	 * value here should be relative to the <code>/public</code> folder listed previously.
	 * 
	 * @return the view name of "hello"
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainview() {
		return "hello";
	}
}
