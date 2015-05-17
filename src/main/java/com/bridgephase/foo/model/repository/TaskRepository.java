package com.bridgephase.foo.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridgephase.foo.model.jpa.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{

}
