package nku.haber.business.abstracts;

import java.util.List;

import nku.haber.core.utilities.results.DataResult;
import nku.haber.core.utilities.results.Result;
import nku.haber.entities.abstracts.CategoryAddDto;
import nku.haber.entities.abstracts.CategoryDeletDto;
import nku.haber.entities.abstracts.CategoryUpdateDao;
import nku.haber.entities.concretes.Category;

public interface CategoryService {
	DataResult<List<Category>> getAll();
	Result addCategory(CategoryAddDto categoryAddDto);
	Result updateCategory(CategoryUpdateDao categoryUpdateDao);
	Result deleteCategory(CategoryDeletDto categoryDeletDto);
}
