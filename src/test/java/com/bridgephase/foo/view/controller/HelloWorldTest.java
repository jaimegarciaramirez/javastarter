package com.bridgephase.foo.view.controller;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.bridgephase.foo.model.jpa.Person;
import com.bridgephase.foo.model.jpa.Task;
import com.bridgephase.foo.model.repository.PersonRepository;
import com.bridgephase.foo.model.repository.TaskRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HelloWorldTest {	
	@Test
	public void sampleTest() {
		Assert.assertEquals("hello", new HelloWorldController().hello());
	}
	
	@Test
	public void addNewTask() {
		// Given
		HelloWorldController myClass = new HelloWorldController();
		myClass.taskRepository = mock(TaskRepository.class);
		setupMockToReturnWhateverWasPassedIn(myClass.taskRepository);
		String description = "My First Task"; 
		
		// When
		Task myTask = myClass.singleTask(description);
		
		// Then
		assertEquals(description, myTask.getTask());
		assertEquals(false, myTask.isCompleted());
	}
	
	private void setupMockToReturnWhateverWasPassedIn(TaskRepository taskRepository) {
		when(taskRepository.save(any(Task.class))).thenAnswer(new Answer<Task>() {
			@Override
			public Task answer(InvocationOnMock invocation) throws Throwable {
				return (Task) invocation.getArguments()[0];
			}
		});
	}

	@Test
	public void addNewTaskSavesToRepository() {
		// Given
		HelloWorldController myClass = new HelloWorldController();
		TaskRepository repository = mock(TaskRepository.class);
		myClass.taskRepository = repository;
		String description = "My First Task"; 
		
		// When
		Task myTask = myClass.singleTask(description);
		
		// Then
		verify(repository).save(argThat(new TaskDescriptionMatcher(description)));
	}

	@Test
	public void showAMock() {
		PersonRepository repository = mock(PersonRepository.class);
		when(repository.findOne(2L)).thenReturn(new Person("John", "Doe"));
		when(repository.findOne(123L)).thenReturn(new Person("John", "Smith"));
		
		System.out.println(repository.findOne(2L));
		repository.save(new Person("John", "Doe"));
		
		verify(repository).save(argThat(new BaseMatcher<Person>() {
			@Override
			public boolean matches(Object item) {
				Person blab = (Person) item;
				return blab.getLastName().equals("Doe");
			}

			@Override
			public void describeTo(Description description) {
			}
		}));
	}
	
}
