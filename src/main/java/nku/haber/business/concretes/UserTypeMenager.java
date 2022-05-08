package nku.haber.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nku.haber.business.abstracts.UserTypeService;
import nku.haber.dataAccess.abstracts.UserTypeDao;
import nku.haber.entities.concretes.UserType;

@Service
public class UserTypeMenager implements UserTypeService {
	
	private UserTypeDao userTypeDao;
	
	@Autowired
	public UserTypeMenager(UserTypeDao userTypeDao) {
		this.userTypeDao = userTypeDao;
	}

	@Override
	public List<UserType> getAll() {
		return this.userTypeDao.findAll();
	}

}
