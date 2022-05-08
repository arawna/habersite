package nku.haber.entities.abstracts;

public class CategoryLeaderAddDto {
	private String token;
	private int userId;
	private int categoryId;
	
	public CategoryLeaderAddDto() {
		super();
	}

	public CategoryLeaderAddDto(String token, int userId, int categoryId) {
		super();
		this.token = token;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
