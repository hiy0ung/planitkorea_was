package org.koreait.planitkorea.controller;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/controller/UserController.java
import jakarta.validation.Valid;
=======
>>>>>>> 8152199 (feat: user 조회, 수정, 탈퇴 추가):PlanItKorea/src/main/java/org/koreait/planitkorea/controller/UserController.java
import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
import org.koreait.planitkorea.dto.ResponseDto;
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/controller/UserController.java
import org.koreait.planitkorea.dto.User.request.DeleteRequestDto;
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/controller/UserController.java
import org.koreait.planitkorea.dto.User.request.UpdatePasswordDto;
=======
>>>>>>> 8152199 (feat: user 조회, 수정, 탈퇴 추가):PlanItKorea/src/main/java/org/koreait/planitkorea/controller/UserController.java
import org.koreait.planitkorea.dto.User.request.UpdateUserRequestDto;
=======
import org.koreait.planitkorea.dto.user.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.user.request.UpdateUserRequestDto;
>>>>>>> abf6421 (refactor: 대소문자변경):PlanItKorea/src/main/java/org/koreait/planitkorea/controller/UserController.java
=======
=======
import jakarta.validation.Valid;
>>>>>>> a1c274a (refactor: 변수명 통일)
import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
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
import org.koreait.planitkorea.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.USER)
public class UserController {

    private final UserService userService;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/controller/UserController.java
=======
>>>>>>> a1c274a (refactor: 변수명 통일)
    public static final String FIND_USER_ID = "/find-id";
    private static final String RESET_PW = "/password";

=======
>>>>>>> 8152199 (feat: user 조회, 수정, 탈퇴 추가):PlanItKorea/src/main/java/org/koreait/planitkorea/controller/UserController.java
=======
    private static final String RESET_PW = "/password";

>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
    // 내 정보 조회
    @GetMapping
    public ResponseEntity<ResponseDto<User>> getMyUserData (
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<User> response = userService.getMyUserData(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 회원 정보 수정
    @PutMapping
    public ResponseEntity<ResponseDto<User>> updateUser (
            @AuthenticationPrincipal Long id,
            @RequestBody UpdateUserRequestDto dto
    ) {
        ResponseDto<User> response = userService.updateUser(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 회원 탈퇴
    @DeleteMapping
    public ResponseEntity<ResponseDto<Boolean>> deleteUser (
            @AuthenticationPrincipal Long id,
            @RequestBody DeleteRequestDto dto
    ) {
        ResponseDto<Boolean> response = userService.deleteUser(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/controller/UserController.java


    // 아이디 찾기
    @GetMapping(FIND_USER_ID)
    public ResponseEntity<ResponseDto<String>> findUserId(
            @Valid @RequestParam String userName,
            @Valid @RequestParam String userPhone
    ) {
        ResponseDto<String> response = userService.findUserId(userName, userPhone);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
=======
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)


    // 아이디 찾기
    @GetMapping(FIND_USER_ID)
    public ResponseEntity<ResponseDto<String>> findUserId(
            @Valid @RequestParam String userName,
            @Valid @RequestParam String userPhone
    ) {
        ResponseDto<String> response = userService.findUserId(userName, userPhone);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(RESET_PW)
    public ResponseEntity<ResponseDto<Boolean>> resetPassword (
            @AuthenticationPrincipal Long id,
            @RequestBody UpdatePasswordDto dto
    ) {
        ResponseDto<Boolean> response = userService.resetPassword(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
<<<<<<< HEAD
=======
>>>>>>> 8152199 (feat: user 조회, 수정, 탈퇴 추가):PlanItKorea/src/main/java/org/koreait/planitkorea/controller/UserController.java
=======
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
}
