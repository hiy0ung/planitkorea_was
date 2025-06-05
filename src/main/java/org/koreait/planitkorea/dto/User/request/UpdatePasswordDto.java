<<<<<<< HEAD
<<<<<<< HEAD
package org.koreait.planitkorea.dto.User.request;
=======
package org.koreait.planitkorea.dto.user.request;
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
=======
package org.koreait.planitkorea.dto.User.request;
>>>>>>> a1c274a (refactor: 변수명 통일)

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePasswordDto {

    @NotBlank
    private String newPassword;

}
