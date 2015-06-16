package com.bridgephase.foo.view.controller;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import com.bridgephase.foo.model.jpa.Task;

public class TaskDescriptionMatcher extends BaseMatcher<Task>{
	private String expected;
	
	public TaskDescriptionMatcher(String expected) {
		this.expected = expected;
	}
	
	@Override
	public void describeTo(Description description) {
	}
	
	@Override
	public boolean matches(Object item) {
		Task toMatch = (Task) item;
		return expected.equals(toMatch.getTask());
	}
}
