package nku.haber.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nku.haber.business.abstracts.CategoryLeaderService;
import nku.haber.core.utilities.results.DataResult;
import nku.haber.core.utilities.results.ErrorDataResult;
import nku.haber.core.utilities.results.ErrorResult;
import nku.haber.core.utilities.results.Result;
import nku.haber.core.utilities.results.SuccessDataResult;
import nku.haber.core.utilities.results.SuccessResult;
import nku.haber.core.utilities.token.TokenService;
import nku.haber.dataAccess.abstracts.CategoryDao;
import nku.haber.dataAccess.abstracts.CategoryLeaderDao;
import nku.haber.dataAccess.abstracts.UserDao;
import nku.haber.entities.abstracts.CategoryLeaderAddDto;
import nku.haber.entities.abstracts.CategoryLeaderDelDto;
import nku.haber.entities.abstracts.CategoryLeaderGetUserIdDto;
import nku.haber.entities.abstracts.CategoryLeaderReturnUserIdDto;
import nku.haber.entities.concretes.Category;
import nku.haber.entities.concretes.CategoryLeader;
import nku.haber.entities.concretes.User;

@Service
public class CategoryLeaderManager implements CategoryLeaderService {

	private CategoryLeaderDao categoryLeaderDao;
	private TokenService tokenService;
	private UserDao userDao;
	private CategoryDao categoryDao;

	@Autowired
	public CategoryLeaderManager(CategoryLeaderDao categoryLeaderDao,TokenService tokenService,UserDao userDao,CategoryDao categoryDao) {
		super();
		this.categoryLeaderDao = categoryLeaderDao;
		this.tokenService = tokenService;
		this.userDao = userDao;
		this.categoryDao = categoryDao;
	}

	@Override
	public DataResult<List<CategoryLeader>> getAll() {
		return new SuccessDataResult<List<CategoryLeader>>(this.categoryLeaderDao.findAll(),"Kategori Liderleri Listelendi");
	}

	@Override
	public Result add(CategoryLeaderAddDto categoryLeaderAddDto) {
		if(this.tokenService.isAdmin(categoryLeaderAddDto.getToken())) {
			
			User user = this.userDao.getById(categoryLeaderAddDto.getUserId());
			if(user == null) {
				return new ErrorResult("Böyle bir kullanıcı yok");
			}
			
			Category category = this.categoryDao.getById(categoryLeaderAddDto.getCategoryId());
			if(category == null) {
				return new ErrorResult("Böyle bir kategori yok");
			}
			
			CategoryLeader categoryLeader = new CategoryLeader();
			categoryLeader.setUser(user);
			categoryLeader.setCategory(category);
			this.categoryLeaderDao.save(categoryLeader);
			return new SuccessResult("Kategori lideri eklendi");
		}else {
			return new ErrorResult("Bunun için yetkiniz yok");
		}
	}

	@Override
	public Result del(CategoryLeaderDelDto categoryLeaderDelDto) {
		if(this.tokenService.isAdmin(categoryLeaderDelDto.getToken())) {
			this.categoryLeaderDao.deleteById(categoryLeaderDelDto.getCategoryLeaderId());
			return new SuccessResult("Kategori lideri silindi");
		}else {
			return new ErrorResult("Bunun için yetkiniz yok");
		}
	}

	@Override
	public DataResult<CategoryLeaderReturnUserIdDto> getByUserId(CategoryLeaderGetUserIdDto categoryLeaderGetUserIdDto) {
		if(tokenService.isAdmin(categoryLeaderGetUserIdDto.getToken()) || tokenService.isModerator(categoryLeaderGetUserIdDto.getToken())) {
			List<CategoryLeader> categoryLeaders = this.categoryLeaderDao.findByUserId(categoryLeaderGetUserIdDto.getUserId());
			List<Category> categories = new ArrayList<Category>();
			for(int i=0;i<categoryLeaders.size();i++) {
				categories.add(categoryLeaders.get(i).getCategory());
			}
			CategoryLeaderReturnUserIdDto categoryLeaderReturnUserIdDto = new CategoryLeaderReturnUserIdDto();
			categoryLeaderReturnUserIdDto.setUser(this.userDao.getById(categoryLeaderGetUserIdDto.getUserId()));
			categoryLeaderReturnUserIdDto.setCategories(categories);
			return new SuccessDataResult<CategoryLeaderReturnUserIdDto>(categoryLeaderReturnUserIdDto,"Data listelendi");
			
		}else {
			return new ErrorDataResult<CategoryLeaderReturnUserIdDto>(null,"Bunun için yetkiniz yok");
		}
	}
	
	
}
