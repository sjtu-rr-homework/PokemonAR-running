package pl.piomin.services.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.piomin.services.user.service.LoginService;
import pl.piomin.services.user.service.RegisterService;

@RestController
public class UserController {
	@Autowired
	private LoginService LoginService;
	@Autowired
	private RegisterService RegisterService;


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





}
