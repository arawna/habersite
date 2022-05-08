package nku.haber.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nku.haber.business.abstracts.UserService;
import nku.haber.core.utilities.results.DataResult;
import nku.haber.core.utilities.results.Result;
import nku.haber.entities.abstracts.ChangeUserTypeDto;
import nku.haber.entities.abstracts.UserLoginDto;
import nku.haber.entities.abstracts.UserLoginReturnDto;
import nku.haber.entities.abstracts.UserLoginWithTokenDto;
import nku.haber.entities.abstracts.UserRegisterDto;
import nku.haber.entities.abstracts.sendAgainVerifyEmailDto;
import nku.haber.entities.concretes.User;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<User>> getAll(){
		return this.userService.getAll();
	}
	
	@PostMapping("/register")
	public Result register(@RequestBody UserRegisterDto userRegisterDto) {
		return this.userService.register(userRegisterDto);
	}
	
	@GetMapping("/mailverify/{text}")
	public Result verifyEmail(@PathVariable String text) {
		return this.userService.verifyEmail(text);
	}
	
	@PostMapping("/login")
	public DataResult<UserLoginReturnDto> login(@RequestBody UserLoginDto userLoginDto){
		return this.userService.login(userLoginDto);
	}
	
	@PostMapping("/loginWithToken")
	public DataResult<UserLoginReturnDto> loginWithToken(@RequestBody UserLoginWithTokenDto userLoginWithTokenDto){
		return this.userService.loginWithToken(userLoginWithTokenDto);
	}
	
	@PostMapping("/changeUserType")
	public Result changeUserType(@RequestBody ChangeUserTypeDto changeUserTypeDto) {
		return this.userService.changeUserType(changeUserTypeDto);
	}
	
	@PostMapping("/sendAgainVerifyEmail")
	public Result sendAgainVerifyEmail(@RequestBody sendAgainVerifyEmailDto againVerifyEmailDto) {
		return this.userService.sendAgainVerifyEmail(againVerifyEmailDto);
	}
}
