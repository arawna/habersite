package nku.haber.business.abstracts;

import java.util.List;

import nku.haber.core.utilities.results.DataResult;
import nku.haber.core.utilities.results.Result;
import nku.haber.entities.abstracts.CategoryLeaderAddDto;
import nku.haber.entities.abstracts.CategoryLeaderDelDto;
import nku.haber.entities.abstracts.CategoryLeaderGetUserIdDto;
import nku.haber.entities.abstracts.CategoryLeaderReturnUserIdDto;
import nku.haber.entities.concretes.CategoryLeader;

public interface CategoryLeaderService {
	DataResult<List<CategoryLeader>> getAll();
	Result add(CategoryLeaderAddDto categoryLeaderAddDto);
	Result del(CategoryLeaderDelDto categoryLeaderDelDto);
	DataResult<CategoryLeaderReturnUserIdDto> getByUserId(CategoryLeaderGetUserIdDto categoryLeaderGetUserIdDto);
}
