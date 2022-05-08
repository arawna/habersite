package nku.haber.business.abstracts;

import java.util.List;

import nku.haber.core.utilities.results.DataResult;
import nku.haber.core.utilities.results.Result;
import nku.haber.entities.abstracts.ChangeUserTypeDto;
import nku.haber.entities.abstracts.UserLoginDto;
import nku.haber.entities.abstracts.UserLoginReturnDto;
import nku.haber.entities.abstracts.UserLoginWithTokenDto;
import nku.haber.entities.abstracts.UserRegisterDto;
import nku.haber.entities.abstracts.sendAgainVerifyEmailDto;
import nku.haber.entities.concretes.User;

public interface UserService {
	DataResult<List<User>> getAll();
	Result register(UserRegisterDto userRegisterDto);
	Result verifyEmail(String text);
	DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto);
	DataResult<UserLoginReturnDto> loginWithToken(UserLoginWithTokenDto userLoginWithTokenDto);
	Result changeUserType(ChangeUserTypeDto changeUserTypeDto);
	Result sendAgainVerifyEmail(sendAgainVerifyEmailDto againVerifyEmailDto);
}
