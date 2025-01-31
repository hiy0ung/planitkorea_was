package org.koreait.planitkorea.repository;

import org.koreait.planitkorea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByUserEmail(String userEmail);
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/repository/UserRepository.java

    User findBySnsIdAndJoinPath(String snsId, String registration);

    Optional<User> findByUserName(String userName);

    Optional<User> findByUserIdAndUserName(String userId, String userName);
<<<<<<< HEAD:src/main/java/org/koreait/planitkorea/repository/UserRepository.java

    Optional<User> findByUserNameAndUserPhone(String userName, String userPhone);
=======
    User findBySnsIdAndJoinPath(String snsId, String registration);

    Optional<User> findByUserName(String userName);
>>>>>>> 6ee97db (feat: OAuth2):PlanItKorea/src/main/java/org/koreait/planitkorea/repository/UserRepository.java
=======
>>>>>>> be57e07 (feat: 비밀번호 찾기, 이메일 전송 로직 추가):PlanItKorea/src/main/java/org/koreait/planitkorea/repository/UserRepository.java
}
