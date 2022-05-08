package nku.haber.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nku.haber.business.abstracts.CategoryService;
import nku.haber.core.utilities.results.DataResult;
import nku.haber.core.utilities.results.ErrorResult;
import nku.haber.core.utilities.results.Result;
import nku.haber.core.utilities.results.SuccessDataResult;
import nku.haber.core.utilities.results.SuccessResult;
import nku.haber.core.utilities.token.TokenService;
import nku.haber.dataAccess.abstracts.CategoryDao;
import nku.haber.entities.abstracts.CategoryAddDto;
import nku.haber.entities.abstracts.CategoryDeletDto;
import nku.haber.entities.abstracts.CategoryUpdateDao;
import nku.haber.entities.concretes.Category;

@Service
public class CategoryManager implements CategoryService {

	private CategoryDao categoryDao;
	private TokenService tokenService;

	@Autowired
	public CategoryManager(CategoryDao categoryDao,TokenService tokenService) {
		super();
		this.categoryDao = categoryDao;
		this.tokenService = tokenService;
	}

	@Override
	public DataResult<List<Category>> getAll() {
		return new SuccessDataResult<List<Category>>(this.categoryDao.findAll(),"Kategoriler Listelendi");
	}

	@Override
	public Result addCategory(CategoryAddDto categoryAddDto) {
		if(tokenService.isAdmin(categoryAddDto.getToken())) {
			Category category = new Category();
			category.setName(categoryAddDto.getName());
			this.categoryDao.save(category);
			return new SuccessResult("Kategori eklendi");
		}else {
			return new ErrorResult("Bunun için yetkiniz yok");
		}
	}

	@Override
	public Result updateCategory(CategoryUpdateDao categoryUpdateDao) {
		if(tokenService.isAdmin(categoryUpdateDao.getToken())) {
			Category category = this.categoryDao.getById(categoryUpdateDao.getId());
			if(category == null) {
				return new ErrorResult("Böyle bir kategori yok");
			}else {
				category.setName(categoryUpdateDao.getName());
				this.categoryDao.save(category);
				return new SuccessResult("Kategori Güncellendi");
			}
		}else {
			return new ErrorResult("Bunun için yetkiniz yok");
		}
	}

	@Override
	public Result deleteCategory(CategoryDeletDto categoryDeletDto) {
		if(tokenService.isAdmin(categoryDeletDto.getToken())) {
			this.categoryDao.deleteById(categoryDeletDto.getCategoryId());
			return new SuccessResult("Kategori silindi");
		}else {
			return new ErrorResult("Bunun için yetkiniz yok");
		}
	}
	
	
}
