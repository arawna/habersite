package nku.haber.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nku.haber.entities.concretes.CategoryLeader;

public interface CategoryLeaderDao extends JpaRepository<CategoryLeader, Integer> {
	List<CategoryLeader> findByUserId(int id);
}
