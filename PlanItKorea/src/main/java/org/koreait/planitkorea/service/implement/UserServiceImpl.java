package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.User.request.DeleteRequestDto;
import org.koreait.planitkorea.dto.User.request.UpdateUserRequestDto;
import org.koreait.planitkorea.entity.User;
import org.koreait.planitkorea.repository.UserRepository;
import org.koreait.planitkorea.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseDto<User> getMyUserData(Long id) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            User user = optionalUser.get();

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<User> updateUser(Long id, UpdateUserRequestDto dto) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            User user = optionalUser.get();

            if(!user.getId().equals(id)) {
                return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
            }

            user = User.builder()
                    .id(user.getId())
                    .userId(user.getUserId())
                    .userPassword(user.getUserPassword())
                    .userName(dto.getUserName())
                    .userBirthDate(user.getUserBirthDate())
                    .userPhone(dto.getUserPhone())
                    .userEmail(dto.getUserEmail())
                    .joinPath(user.getJoinPath())
                    .snsId(user.getSnsId())
                    .build();
            userRepository.save(user);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, user);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<Boolean> deleteUser(Long id, DeleteRequestDto dto) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            User user = optionalUser.get();
            userRepository.delete(user);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }


}
