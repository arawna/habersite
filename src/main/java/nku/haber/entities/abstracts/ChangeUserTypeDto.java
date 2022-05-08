package nku.haber.entities.abstracts;

public class ChangeUserTypeDto {
	private String token;
	
	private int userId;
	
	private int typeId;

	public ChangeUserTypeDto() {
		super();
	}

	public ChangeUserTypeDto(String token, int userId, int typeId) {
		super();
		this.token = token;
		this.userId = userId;
		this.typeId = typeId;
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	
}
