package org.pokemonrun.controller;

import org.pokemonrun.info.Coverinfo;
import org.pokemonrun.info.UserInfoForAdmin;
import org.pokemonrun.info.Userinfo;
import org.pokemonrun.service.GetUserInfo;
import org.pokemonrun.service.ModifyUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.pokemonrun.service.LoginService;
import org.pokemonrun.service.RegisterService;

import java.util.List;
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

	@GetMapping("/admingetuserinfo/username/{username}")
	public UserInfoForAdmin AdminGetUserInfo(@PathVariable("username") String username)
	{
		return  GetUserInfo.AdminGetUserInfo(username);
	}

	@GetMapping("/setpet/username/{username}/setpet/{pet}")
	public boolean UserSetPet(@PathVariable("username") String username, @PathVariable("pet") String pet)
	{
		return ModifyUserInfo.SetPet(username,Integer.parseInt(pet));
	}

	@GetMapping("/blockuser/username/{username}")
	public boolean BlockUser(@PathVariable("username") String username)
	{
		return ModifyUserInfo.blockUser(username);
	}
	@GetMapping("/addDistance/username/{username}/distance/{distance}")
	public boolean addDistance(@PathVariable("username") String username, @PathVariable("distance") String distance)
	{
		return ModifyUserInfo.AddDistance(username,Double.parseDouble(distance));
	}
	@GetMapping("/getpet/username/{username}")
	public int getPet(@PathVariable("username") String username)
	{
		return GetUserInfo.GetPet(username);
	}
	@GetMapping("/addfriend/username/{username}/friendname/{friendname}")
	public boolean addFriend(@PathVariable("username") String username,@PathVariable("friendname") String friendname)
	{
		return ModifyUserInfo.addFriend(username, friendname);
	}
	@GetMapping("/getallusername")
	public List<String> getAllUsername()
	{
		return GetUserInfo.GetAllUser();
	}
	@PostMapping("/add/cover")
	public boolean addCover(@RequestBody Coverinfo Coverinfo)
	{
		return ModifyUserInfo.addCover(Coverinfo);
	}
	@GetMapping("/get/cover/username/{username}")
	public byte[] getCover(@PathVariable("username") String username)
	{
		return GetUserInfo.getCover(username);
	}
	@GetMapping("/getexp/username/{username}")
	public int getExp(@PathVariable("username") String username)
	{
		return GetUserInfo.getExp(username);

	}

}
