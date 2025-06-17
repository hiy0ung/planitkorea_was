package org.koreait.planitkorea.dto.User.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePasswordDto {

    @NotBlank
    private String newPassword;

}
