package nku.haber.entities.abstracts;

import nku.haber.entities.concretes.User;

public class UserLoginReturnDto {
	private String token;
	private User user;

	public UserLoginReturnDto() {
	}

	

	public UserLoginReturnDto(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
	
}
