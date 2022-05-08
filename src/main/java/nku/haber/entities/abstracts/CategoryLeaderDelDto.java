package nku.haber.entities.abstracts;

public class CategoryLeaderDelDto {
	private String token;
	private int categoryLeaderId;
	
	public CategoryLeaderDelDto() {
		super();
	}

	public CategoryLeaderDelDto(String token, int categoryLeaderId) {
		super();
		this.token = token;
		this.categoryLeaderId = categoryLeaderId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getCategoryLeaderId() {
		return categoryLeaderId;
	}

	public void setCategoryLeaderId(int categoryLeaderId) {
		this.categoryLeaderId = categoryLeaderId;
	}
	
	
}
