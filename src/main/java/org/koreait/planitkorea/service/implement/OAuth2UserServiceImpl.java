package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.object.CustomOAuth2User;
import org.koreait.planitkorea.entity.User;
import org.koreait.planitkorea.provider.JwtProvider;
import org.koreait.planitkorea.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(request);
        String registration = request.getClientRegistration().getClientName().toLowerCase();

        String snsId = getSnsId(oAuth2User, registration);

        User user = userRepository.findBySnsIdAndJoinPath(snsId, registration);

        CustomOAuth2User customOAuth2User = null;

        if (user == null) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("snsId", snsId);
            attributes.put("joinPath", registration);

            customOAuth2User = new CustomOAuth2User(snsId, attributes, false);
        } else {
            Long id = user.getId();
            String userName = user.getUserName();
            String token = jwtProvider.generateJwtToken(id);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("accessToken", token);

            customOAuth2User = new CustomOAuth2User(userName, attributes, true);
        }

        return customOAuth2User;

    }

    private String getSnsId(OAuth2User oAuth2User, String registration) {
        String snsId = null;

        if (registration.equals("kakao")) {
            snsId = oAuth2User.getName();
        }
        if (registration.equals("naver")) {
            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("response");
            snsId = response.get("id");
        }

        return snsId;
    }

}

