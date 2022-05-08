package nku.haber.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import nku.haber.core.utilities.Cryiption.AES;
import nku.haber.core.utilities.token.TokenService;
import nku.haber.entities.abstracts.UserLoginReturnDto;
import nku.haber.entities.concretes.User;

@RestController
@RequestMapping("/api/deneme")
@CrossOrigin
public class DenemeController {
	
	private AES aes;
	private ObjectMapper mapper;
	private TokenService tokenService;
	
	
	@Autowired
	public DenemeController(AES aes,ObjectMapper mapper,TokenService tokenService) {
		super();
		this.aes = aes;
		this.mapper = mapper;
		this.tokenService = tokenService;
	}



	@PostMapping("/deneme")
	public User deneme(@RequestBody UserLoginReturnDto userLoginReturnDto) {
		return this.tokenService.getUserWithToken(userLoginReturnDto.getToken());
	}
	
	
}
