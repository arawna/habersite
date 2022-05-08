package nku.haber.entities.abstracts;

public class CategoryDeletDto {
	
	private int categoryId;
	private String token;
	
	public CategoryDeletDto() {
		super();
	}

	public CategoryDeletDto(int categoryId, String token) {
		super();
		this.categoryId = categoryId;
		this.token = token;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
