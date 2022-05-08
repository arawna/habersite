package nku.haber.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import nku.haber.business.abstracts.UserService;
import nku.haber.core.utilities.Cryiption.AES;
import nku.haber.core.utilities.mail.MailManager;
import nku.haber.core.utilities.mail.ValidMail;
import nku.haber.core.utilities.results.DataResult;
import nku.haber.core.utilities.results.ErrorDataResult;
import nku.haber.core.utilities.results.ErrorResult;
import nku.haber.core.utilities.results.Result;
import nku.haber.core.utilities.results.SuccessDataResult;
import nku.haber.core.utilities.results.SuccessResult;
import nku.haber.core.utilities.token.TokenService;
import nku.haber.dataAccess.abstracts.UserDao;
import nku.haber.dataAccess.abstracts.UserTypeDao;
import nku.haber.entities.abstracts.ChangeUserTypeDto;
import nku.haber.entities.abstracts.UserLoginDto;
import nku.haber.entities.abstracts.UserLoginReturnDto;
import nku.haber.entities.abstracts.UserLoginWithTokenDto;
import nku.haber.entities.abstracts.UserRegisterDto;
import nku.haber.entities.abstracts.sendAgainVerifyEmailDto;
import nku.haber.entities.concretes.User;
import nku.haber.entities.concretes.UserType;

@Service
public class UserManager implements UserService {

	private UserDao userDao;
	private UserTypeDao userTypeDao;
	private ValidMail validMail;
	private MailManager mailManager;
	private AES aes;
	private TokenService tokenService;
	
	@Autowired
	public UserManager(UserDao userDao,UserTypeDao userTypeDao,ValidMail validMail,MailManager mailManager,AES aes,TokenService tokenService) {
		super();
		this.userDao = userDao;
		this.userTypeDao = userTypeDao;
		this.validMail = validMail;
		this.mailManager = mailManager;
		this.aes = aes;
		this.tokenService = tokenService;
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll(),"Data Listelendi");
	}

	@Override
	public Result register(UserRegisterDto userRegisterDto) {
		if(this.userDao.findByEmail(userRegisterDto.getEmail()) != null) {
			return new ErrorResult("Bu email ile kayıtlı bir kullanıcı var");
		}else if(!validMail.isEmailValid(userRegisterDto.getEmail())) {
			return new ErrorResult("Geçerli bir email giriniz");
		}else {
			User user = new User();
			user.setEmail(userRegisterDto.getEmail());
			user.setName(userRegisterDto.getName());
			user.setSurname(userRegisterDto.getSurname());
			user.setPass(userRegisterDto.getPass());
			user.setUserType(userTypeDao.getById(3));
			user.setMailVerify(false);
			userDao.save(user);
			
			String mailText = "Mail adresinizi onaylamak için linke tıklayınız http://localhost:8080/api/user/mailverify/";
			mailText += UriUtils.encode(aes.encrypt(userRegisterDto.getEmail(), "123456789"), "UTF-8");
			mailManager.sendMail(userRegisterDto.getEmail(),"Nkü Haber Kayıt",mailText);
			
			return new SuccessResult("Kayıt yapıldı lütfen mailinize gelen mail ile mailinizi onaylayınız");
		}
	}

	@Override
	public Result verifyEmail(String text) {
		try {
			String email = aes.decrypt(UriUtils.decode(text, "UTF-8"), "123456789");
			User user = this.userDao.findByEmail(email);
			user.setMailVerify(true);
			this.userDao.save(user);
			
			return new SuccessResult("Mail onaylandı");
		} catch (Exception e) {
			return new ErrorResult("Bir hata oluştu");
		}
	}

	@Override
	public DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto) {
		User user = this.userDao.findByEmail(userLoginDto.getEmail());
		if(user != null) {
			if(!user.isMailVerify()) {
				return new ErrorDataResult<UserLoginReturnDto>(null,"Giriş yapmak için önce mail adresinizi onaylamalısınız");
			}
			if(user.getPass().equals(userLoginDto.getPass())) {
				return new SuccessDataResult<UserLoginReturnDto>(new UserLoginReturnDto(tokenService.generateToken(user),user),"Giriş yapıldı");
			}else {
				return new ErrorDataResult<UserLoginReturnDto>(null, "Hatalı şifre");
			}
		}else {
			return new ErrorDataResult<UserLoginReturnDto>(null, "Böyle bir kullanıcı yok");
		}
	}

	@Override
	public DataResult<UserLoginReturnDto> loginWithToken(UserLoginWithTokenDto userLoginWithTokenDto) {
		try {
			return new SuccessDataResult<UserLoginReturnDto>(new UserLoginReturnDto(userLoginWithTokenDto.getToken(),this.tokenService.getUserWithToken(userLoginWithTokenDto.getToken())),"Giriş yapıldı");
		} catch (Exception e) {
			return new ErrorDataResult<UserLoginReturnDto>(null,"Bir hata oluştu");
		}
	}

	@Override
	public Result changeUserType(ChangeUserTypeDto changeUserTypeDto) {
		if(this.tokenService.isAdmin(changeUserTypeDto.getToken())) {
			User user = this.userDao.getById(changeUserTypeDto.getUserId());
			UserType userType = this.userTypeDao.getById(changeUserTypeDto.getTypeId());
			
			if(user == null) {
				return new ErrorResult("Böyle bir kullanıcı yok");
			}else if(userType == null) {
				return new ErrorResult("Böyle bir kullanıcı tipi yok");
			}else {
				user.setUserType(userType);
				this.userDao.save(user);
				return new SuccessResult("Kullanıcının tipi değiştirildi");
			}
		}else {
			return new ErrorResult("Bunun için yetkiniz yok");
		}
	}

	@Override
	public Result sendAgainVerifyEmail(sendAgainVerifyEmailDto againVerifyEmailDto) {
		User user = this.userDao.findByEmail(againVerifyEmailDto.getEmail());
		if(user == null) {
			return new ErrorResult("Bu email ile kayıtlı bir kullanıcı yok");
		}else {
			try {
				String mailText = "Mail adresinizi onaylamak için linke tıklayınız http://localhost:8080/api/user/mailverify/";
				mailText += UriUtils.encode(aes.encrypt(againVerifyEmailDto.getEmail(), "123456789"), "UTF-8");
				mailManager.sendMail(againVerifyEmailDto.getEmail(),"Nkü Haber Kayıt",mailText);
				
				return new SuccessResult("Onay maili gönderildi");
			} catch (Exception e) {
				return new ErrorResult("Bir hata oluştu");
			}
			
		}
	}

}
