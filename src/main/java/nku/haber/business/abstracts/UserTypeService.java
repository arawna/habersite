package nku.haber.business.abstracts;

import java.util.List;

import nku.haber.entities.concretes.UserType;

public interface UserTypeService {
	List<UserType> getAll();
}
