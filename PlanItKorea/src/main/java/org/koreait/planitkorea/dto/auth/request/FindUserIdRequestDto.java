package org.koreait.planitkorea.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindUserIdRequestDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String userPhone;
}
