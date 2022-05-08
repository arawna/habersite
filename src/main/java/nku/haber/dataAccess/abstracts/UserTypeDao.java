package nku.haber.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import nku.haber.entities.concretes.UserType;

public interface UserTypeDao extends JpaRepository<UserType, Integer> {

}
