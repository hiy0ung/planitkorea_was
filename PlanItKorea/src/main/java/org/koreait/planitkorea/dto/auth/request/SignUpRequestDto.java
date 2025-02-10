package org.koreait.planitkorea.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String userPassword;

    @NotBlank
    private String checkPassword;

    @NotBlank
    private String userName;

    @NotBlank
    private String userBirthDate;

    @NotBlank
    private String userPhone;

    @NotBlank
    private String userEmail;

    @NotBlank
    @Pattern(regexp="^(home|kakao|naver)$")
    private String joinPath;
    private String snsId;
}
