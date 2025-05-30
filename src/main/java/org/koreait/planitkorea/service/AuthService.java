package org.koreait.planitkorea.service;

import jakarta.validation.Valid;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.auth.request.*;
import org.koreait.planitkorea.dto.auth.response.*;

public interface AuthService {
    ResponseDto<SignUpResponseDto> signUp(@Valid SignUpRequestDto dto);

    ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto dto);

    ResponseDto<UserIdDuplicationResponseDto> userIdDuplicationCheck(@Valid UserIdDuplicationRequestDto dto);

    ResponseDto<UserEmailDuplicationResponseDto> userEmailDuplicationCheck(@Valid UserEmailDuplicationRequestDto dto);

    ResponseDto<String> findUserId(@Valid String userName, @Valid String userPhone);
}
