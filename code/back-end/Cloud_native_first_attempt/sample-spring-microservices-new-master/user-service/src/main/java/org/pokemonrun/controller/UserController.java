package org.pokemonrun.controller;

import org.pokemonrun.info.Userinfo;
import org.pokemonrun.service.GetUserInfo;
import org.pokemonrun.service.ModifyUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.pokemonrun.service.LoginService;
import org.pokemonrun.service.RegisterService;

@RestController
public class UserController {
	@Autowired
	private LoginService LoginService;
	@Autowired
	private RegisterService RegisterService;
	@Autowired
	private GetUserInfo GetUserInfo;
	@Autowired
	private ModifyUserInfo ModifyUserInfo;


	@GetMapping("/register/username/{username}/password/{password}/email/{email}")
	public boolean register(@PathVariable("username") String username, @PathVariable("password") String password,@PathVariable("email") String email)
	{
		return RegisterService.Register(username,password,email,0);
	}

	@GetMapping("/login/username/{username}/password/{password}")
	public boolean login(@PathVariable("username") String username, @PathVariable("password") String password)
	{
		return LoginService.Login(username, password);
	}

	@GetMapping("/addexp/username/{username}/exp/{exp}")
	public boolean addExp(@PathVariable("username") String username, @PathVariable("exp") String exp)
	{
		return ModifyUserInfo.ModifyExp(username, Integer.parseInt(exp));
	}

	@GetMapping("/getinfo/username/{username}")
	public Userinfo getInfo(@PathVariable("username") String username)
	{
		return GetUserInfo.getUserInfo(username);
	}




}
