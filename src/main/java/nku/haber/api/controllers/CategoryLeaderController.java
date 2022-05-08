package nku.haber.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nku.haber.business.abstracts.CategoryLeaderService;
import nku.haber.core.utilities.results.DataResult;
import nku.haber.core.utilities.results.Result;
import nku.haber.entities.abstracts.CategoryLeaderAddDto;
import nku.haber.entities.abstracts.CategoryLeaderDelDto;
import nku.haber.entities.abstracts.CategoryLeaderGetUserIdDto;
import nku.haber.entities.abstracts.CategoryLeaderReturnUserIdDto;
import nku.haber.entities.concretes.CategoryLeader;

@RestController
@RequestMapping("/api/categoryLeader")
@CrossOrigin
public class CategoryLeaderController {
	
	private CategoryLeaderService categoryLeaderService;

	@Autowired
	public CategoryLeaderController(CategoryLeaderService categoryLeaderService) {
		super();
		this.categoryLeaderService = categoryLeaderService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<CategoryLeader>> getAll(){
		return this.categoryLeaderService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody CategoryLeaderAddDto categoryLeaderAddDto) {
		return this.categoryLeaderService.add(categoryLeaderAddDto);
	}
	
	@PostMapping("/del")
	public Result del(@RequestBody CategoryLeaderDelDto categoryLeaderDelDto) {
		return this.categoryLeaderService.del(categoryLeaderDelDto);
	}
	
	@PostMapping("/getByUserId")
	public DataResult<CategoryLeaderReturnUserIdDto> getByUserId(@RequestBody CategoryLeaderGetUserIdDto categoryLeaderGetUserIdDto){
		return this.categoryLeaderService.getByUserId(categoryLeaderGetUserIdDto);
	}
}
