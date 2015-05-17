package com.bridgephase.foo.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgephase.foo.model.jpa.Person;
import com.bridgephase.foo.model.jpa.Task;
import com.bridgephase.foo.model.repository.PersonRepository;
import com.bridgephase.foo.model.repository.TaskRepository;

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
	
	@Autowired
	protected TaskRepository taskRepository;
	
	/**
	 * This request mapping method will render a response of "Hello World".
	 * 
	 * @return A rendered response body of "Hello World"
	 */
	@RequestMapping(value = "/helloworld")
	@ResponseBody
	public Iterable<Person> sayHello() {
		Person person = new Person("John", "Doe");
		person = personRepository.save(person);
		return personRepository.findAll();
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public Iterable<Person> listAll() {
		return personRepository.findAll();
	}
	
	@RequestMapping(value = "/AddTask",method=RequestMethod.POST)
	@ResponseBody
	public Task singleTask(@RequestBody String task) {
		System.out.println(String.format("Added a task: %s", task));
		Task myTask = new Task();
		myTask.setTask(task);
		myTask = taskRepository.save(myTask);
		return myTask;
	}
	
	@RequestMapping(value = "/listTasks")
	@ResponseBody
	public Iterable<Task> listAllTasks() {
		return taskRepository.findAll();
	}

	/**
	 * This request mapping method will render a view. The views are
	 * stored under the <code>/src/main/resources/public</code> directory, the returned
	 * value here should be relative to the <code>/public</code> folder listed previously.
	 * 
	 * @return the view name of "hello"
	 */
	@RequestMapping(value = "/")
	public String hello() {
		return "hello";
	}
}
