package nku.haber.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import nku.haber.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
