//package org.ohmystomach.ohmystomach_server.domain.oauth.application;
//
//import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 사용자 정보를 데이터베이스 또는 기타 소스에서 로드
//        // 예시로 사용자 이름과 패스워드를 간단히 반환
//        return new User(username, "password", new ArrayList<>());
//    }
//}