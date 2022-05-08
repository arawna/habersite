package nku.haber.entities.abstracts;

public class CategoryUpdateDao {
	
	private int id;
	private String name;
	private String token;
	
	public CategoryUpdateDao() {
		super();
	}

	public CategoryUpdateDao(int id, String name, String token) {
		super();
		this.id = id;
		this.name = name;
		this.token = token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
