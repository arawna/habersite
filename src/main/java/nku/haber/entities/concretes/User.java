package nku.haber.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "pass")
	private String pass;
	
	private boolean mailVerify;
	
	@ManyToOne()
	@JoinColumn(name = "user_type_id")
	private UserType userType;

	public User() {
	}

	public User(int id, String name, String surname, String email, String pass, UserType userType, boolean mailVerify) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.pass = pass;
		this.userType = userType;
		this.mailVerify = mailVerify;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public boolean isMailVerify() {
		return mailVerify;
	}

	public void setMailVerify(boolean mailVerify) {
		this.mailVerify = mailVerify;
	}
	
	
}
