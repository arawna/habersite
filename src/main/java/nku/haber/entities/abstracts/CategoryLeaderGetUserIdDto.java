package nku.haber.entities.abstracts;

public class CategoryLeaderGetUserIdDto {
	private String token;
	private int userId;
	
	public CategoryLeaderGetUserIdDto() {
		super();
	}
	public CategoryLeaderGetUserIdDto(String token, int userId) {
		super();
		this.token = token;
		this.userId = userId;
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
	
	
}
