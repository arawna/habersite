package nku.haber.entities.abstracts;

public class UserLoginWithTokenDto {
	private String token;

	public UserLoginWithTokenDto() {
		super();
	}

	public UserLoginWithTokenDto(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
