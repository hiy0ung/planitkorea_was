package org.koreait.planitkorea.service;

<<<<<<< HEAD
<<<<<<< HEAD
import jakarta.validation.Valid;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.User.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.User.request.UpdatePasswordDto;
import org.koreait.planitkorea.dto.User.request.UpdateUserRequestDto;
=======
=======
import jakarta.validation.Valid;
>>>>>>> a1c274a (refactor: 변수명 통일)
import org.koreait.planitkorea.dto.ResponseDto;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.koreait.planitkorea.dto.user.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.user.request.UpdateUserRequestDto;
=======
import org.koreait.planitkorea.dto.User.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.User.request.UpdatePasswordDto;
import org.koreait.planitkorea.dto.User.request.UpdateUserRequestDto;
>>>>>>> 1d9962e (20250131 pyj password)
=======
import org.koreait.planitkorea.dto.user.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.user.request.UpdatePasswordDto;
import org.koreait.planitkorea.dto.user.request.UpdateUserRequestDto;
>>>>>>> d5b8dc4 (20250131 shy)
<<<<<<< HEAD
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
=======
=======
import org.koreait.planitkorea.dto.User.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.User.request.UpdatePasswordDto;
import org.koreait.planitkorea.dto.User.request.UpdateUserRequestDto;
>>>>>>> 28d444e (refactor: 변수명 통일)
>>>>>>> a1c274a (refactor: 변수명 통일)
import org.koreait.planitkorea.entity.User;

public interface UserService {
    ResponseDto<User> updateUser(Long id, UpdateUserRequestDto dto);

    ResponseDto<Boolean> deleteUser(Long id, DeleteRequestDto dto);

    ResponseDto<User> getMyUserData(Long id);

<<<<<<< HEAD
<<<<<<< HEAD
    ResponseDto<String> findUserId(@Valid String userName, @Valid String userPhone);

=======
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
=======
    ResponseDto<String> findUserId(@Valid String userName, @Valid String userPhone);

>>>>>>> a1c274a (refactor: 변수명 통일)
    ResponseDto<Boolean> resetPassword(Long id, UpdatePasswordDto dto);
}
