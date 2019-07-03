package com.example.demo.domains.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domains.entities.Actor;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.infraestructure.repositories.ActorRepository;

@Service
public class ActorService {
	@Autowired
	private ActorRepository dao;

	@Autowired
	private Validator validator;

	public Set<ConstraintViolation<Actor>> validate(Actor item) {
		Set<ConstraintViolation<Actor>> rslt = validator.validate(item);
//		if(!item.getFirstName().equals(item.getFirstName().toUpperCase()))
//			rslt.add(new ConstraintViolation<Actor>() {
//				
//			})
		return rslt;
	}

	public boolean isValid(Actor item) {
		return validate(item).size() == 0;
	}

	public boolean isNotValid(Actor item) {
		return !isValid(item);
	}

	public List<Actor> getAll() {
		return dao.findAll();
	}

	public Actor get(int id) throws NotFoundException {
		Optional<Actor> rslt = dao.findById(id);
		if (!rslt.isPresent())
			throw new NotFoundException();
		return rslt.get();
	}

	@Transactional
	public Actor add(Actor item) throws InvalidDataException {
		if (item == null || isNotValid(item))
			throw new InvalidDataException("Datos no validos");
		if (dao.findById(item.getActorId()).isPresent())
			throw new InvalidDataException("Duplicate key");
		return dao.save(item);
	}

	@Transactional
	public Actor change(Actor item) throws InvalidDataException, NotFoundException {
		if (item == null || isNotValid(item))
			throw new InvalidDataException("Datos no validos");
		if (!dao.findById(item.getActorId()).isPresent())
			throw new NotFoundException();
		return dao.save(item);
	}

	@Transactional
	public void delete(int id) throws NotFoundException {
		if (!dao.findById(id).isPresent())
			throw new NotFoundException();
		dao.deleteById(id);
	}

	@Transactional
	public void delete(Actor item) throws NotFoundException, InvalidDataException {
		if (item == null || isNotValid(item))
			throw new InvalidDataException("Datos no validos");
		delete(item.getActorId());
	}

}
