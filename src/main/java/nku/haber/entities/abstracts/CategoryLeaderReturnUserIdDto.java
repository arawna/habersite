package nku.haber.entities.abstracts;

import java.util.List;

import nku.haber.entities.concretes.Category;
import nku.haber.entities.concretes.User;

public class CategoryLeaderReturnUserIdDto {
	private User user;
	private List<Category> categories;
	public CategoryLeaderReturnUserIdDto() {
		super();
	}
	public CategoryLeaderReturnUserIdDto(User user, List<Category> categories) {
		super();
		this.user = user;
		this.categories = categories;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	
}
