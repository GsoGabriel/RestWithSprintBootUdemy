package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	public Person create(Person person) {
		return repository.save(person);
	}
	
	public List<Person> findAll() {
		return repository.findAll();
	}

	public Person findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
	}
	
	public Person update(Person person) {
		Person entity = findById(person.getId());
		person.setId(person.getId());
		person.setFirstName(person.getFirstName());
		person.setLastName(person.getLastName());
		person.setAddress(person.getAddress());
		person.setGender(person.getGender());
		return repository.save(entity);
	}

	public void delete(Long id) {
		Person entity = findById(id);
		repository.delete(entity);
	}
	
	

}
