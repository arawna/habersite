package nku.haber.entities.abstracts;

public class UserRegisterDto {
	private String name;
	
	private String surname;
	
	private String email;
	
	private String pass;

	public UserRegisterDto() {
	}

	public UserRegisterDto(String name, String surname, String email, String pass) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
}
