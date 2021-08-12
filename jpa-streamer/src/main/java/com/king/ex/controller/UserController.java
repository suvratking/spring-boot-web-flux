package com.king.ex.controller;

import com.king.ex.model.FilmViewModel;
import com.king.ex.model.User;
import com.king.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping(value = "/save")
	public User saveUser(@RequestBody User user) {
		return service.addUser(user);
	}

	@GetMapping("/getUsers")
	public List<User> findAllUsers() {
		return service.getUsers();
	}

	@GetMapping("/getUserByAddress/{address}")
	public List<User> findUserByAddress(@PathVariable String address) {
		return service.getUserByAddress(address);
	}

	@DeleteMapping(value="/remove")
	public User removeUser(@RequestBody User user) {
		service.deleteUser(user);
		return user;
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "/films", produces = MediaType.APPLICATION_JSON_VALUE)
	public Stream<FilmViewModel> films(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int pageSize
	) {
		return service.filmDetails(page, pageSize);
	}

	@PostConstruct
	public void addUser() {
		service.saveAll(List.of(
				new User(1, "ABC", 25, "BBSR"),
				new User(2, "ABCD", 26, "BBSR"),
				new User(3, "ABCDE", 27, "BBSR"),
				new User(4, "ABCDEF", 28, "BBSR")));
		List<User> users2 = service.getUsers2();
		System.out.println(users2);
		List<User> us = service.getUserByName("ABC");
		System.out.println(us);
	}
}
