package nku.haber.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nku.haber.business.abstracts.UserTypeService;
import nku.haber.entities.concretes.UserType;

@RestController
@RequestMapping("/api/userTypes")
@CrossOrigin
public class UserTypesController {

	private UserTypeService userTypeService;

	@Autowired
	public UserTypesController(UserTypeService userTypeService) {
		super();
		this.userTypeService = userTypeService;
	}
	
	@GetMapping("/getAll")
	public List<UserType> getAll(){
		return this.userTypeService.getAll();
	}
	
}
