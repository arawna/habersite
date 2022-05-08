package nku.haber.core.utilities.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import nku.haber.core.utilities.Cryiption.AES;
import nku.haber.entities.concretes.User;

@Service
public class TokenService {

	private AES aes;
	private ObjectMapper mapper;
	private Gson gson;

	@Autowired
	public TokenService(AES aes,ObjectMapper mapper,Gson gson) {
		this.aes = aes;
		this.mapper = mapper;
		this.gson = gson;
	}
	
	public String generateToken(User user) {
		try {
			return this.aes.encrypt(mapper.writeValueAsString(user), "123456789");
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public User getUserWithToken(String token) {
		try {
			return gson.fromJson(aes.decrypt(token, "123456789"), User.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean isAdmin(String token) {
		try {
			if(gson.fromJson(aes.decrypt(token, "123456789"), User.class).getUserType().getType().equals("admin")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isModerator(String token) {
		try {
			if(gson.fromJson(aes.decrypt(token, "123456789"), User.class).getUserType().getType().equals("moderator")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
}
