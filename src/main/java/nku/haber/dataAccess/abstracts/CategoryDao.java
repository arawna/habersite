package nku.haber.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import nku.haber.entities.concretes.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {

}
