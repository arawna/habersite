package nku.haber.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nku.haber.business.abstracts.CategoryService;
import nku.haber.core.utilities.results.DataResult;
import nku.haber.core.utilities.results.Result;
import nku.haber.entities.abstracts.CategoryAddDto;
import nku.haber.entities.abstracts.CategoryDeletDto;
import nku.haber.entities.abstracts.CategoryUpdateDao;
import nku.haber.entities.concretes.Category;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Category>> getAll(){
		return this.categoryService.getAll();
	}
	
	@PostMapping("/add")
	public Result addCategory(CategoryAddDto categoryAddDto) {
		return this.categoryService.addCategory(categoryAddDto);
	}
	
	@PostMapping("/update")
	public Result updateCategory(CategoryUpdateDao categoryUpdateDao) {
		return this.categoryService.updateCategory(categoryUpdateDao);
	}
	
	@PostMapping("/delete")
	Result deleteCategory(CategoryDeletDto categoryDeletDto) {
		return this.categoryService.deleteCategory(categoryDeletDto);
	}
	
}
