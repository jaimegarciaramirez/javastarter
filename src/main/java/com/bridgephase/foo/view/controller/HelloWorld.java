package com.bridgephase.foo.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This is a sample controller definition which shows both a <code>ResponseBody</code> response as well as a view
 * mapped response.
 * 
 * @author Jaime Garcia
 */
@Controller
public class HelloWorld {

	/**
	 * This request mapping method will render a response of "Hello World".
	 * 
	 * @return A rendered response body of "Hello World"
	 */
	@RequestMapping(value = "/helloworld")
	@ResponseBody
	public String sayHello() {
		return "Hello World";
	}

	/**
	 * This request mapping method will render a view. The views are
	 * stored under the <code>/src/main/resources/public</code> directory, the returned
	 * value here should be relative to the <code>/public</code> folder listed previously.
	 * 
	 * @return the view name of "hello"
	 */
	@RequestMapping(value = "/hello")
	public String hello() {
		return "hello";
	}
}
