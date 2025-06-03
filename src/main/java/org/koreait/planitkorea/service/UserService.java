package org.koreait.planitkorea.service;

import jakarta.validation.Valid;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.User.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.User.request.UpdatePasswordDto;
import org.koreait.planitkorea.dto.User.request.UpdateUserRequestDto;
import org.koreait.planitkorea.entity.User;

public interface UserService {
    ResponseDto<User> updateUser(Long id, UpdateUserRequestDto dto);

    ResponseDto<Boolean> deleteUser(Long id, DeleteRequestDto dto);

    ResponseDto<User> getMyUserData(Long id);

    ResponseDto<String> findUserId(@Valid String userName, @Valid String userPhone);

    ResponseDto<Boolean> resetPassword(Long id, UpdatePasswordDto dto);
}
