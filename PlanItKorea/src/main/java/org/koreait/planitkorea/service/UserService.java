package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.User.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.User.request.UpdateUserRequestDto;
import org.koreait.planitkorea.entity.User;

public interface UserService {
    ResponseDto<User> updateUser(Long id, UpdateUserRequestDto dto);

    ResponseDto<Boolean> deleteUser(Long id, DeleteRequestDto dto);

    ResponseDto<User> getMyUserData(Long id);
}
