package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.ResponseDto;
<<<<<<< HEAD
import org.koreait.planitkorea.dto.user.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.user.request.UpdateUserRequestDto;
=======
import org.koreait.planitkorea.dto.User.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.User.request.UpdatePasswordDto;
import org.koreait.planitkorea.dto.User.request.UpdateUserRequestDto;
>>>>>>> 1d9962e (20250131 pyj password)
import org.koreait.planitkorea.entity.User;

public interface UserService {
    ResponseDto<User> updateUser(Long id, UpdateUserRequestDto dto);

    ResponseDto<Boolean> deleteUser(Long id, DeleteRequestDto dto);

    ResponseDto<User> getMyUserData(Long id);

    ResponseDto<Boolean> resetPassword(Long id, UpdatePasswordDto dto);
}
