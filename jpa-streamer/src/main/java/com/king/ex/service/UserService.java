package com.king.ex.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.king.ex.model.Film;
import com.king.ex.model.FilmViewModel;
import com.king.ex.model.User$;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.ex.dao.UserRepository;
import com.king.ex.model.User;
import com.speedment.jpastreamer.application.JPAStreamer;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private JPAStreamer jpaStreamer;

	public User addUser(User user) {
		return repository.save(user);
	}
	
	public List<User> getUsers2(){
		return jpaStreamer.stream(User.class).collect(Collectors.toList());
	}
	
	public List<User> getUserByName(String name) {
		return jpaStreamer.stream(User.class)
				.filter(User$.name.equal(name))
				.collect(Collectors.toList());
	}

	public List<User> getUsers() {
		List<User> users = repository.findAll();
		System.out.println("Getting data from DB : " + users);
		return users;
	}

	public List<User> getUserByAddress(String address) {
		return repository.findByAddress(address);
	}

	public void deleteUser(User user) {
		repository.delete(user);
	}

	public void saveAll(List<User> users) {
		repository.saveAll(users);
	}

	public Stream<FilmViewModel> filmDetails(int page, int pageSize) {
		return jpaStreamer.stream(Film.class)
				.skip((long) page * pageSize)
				.limit(pageSize)
				.map(FilmViewModel::from);
	}
}
