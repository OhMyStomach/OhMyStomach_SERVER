//package org.ohmystomach.ohmystomach_server.domain.oauth.dto;
//
//import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final User user;
//
//    public CustomUserDetails(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // 예시: 사용자 권한 반환
//        return Collections.emptyList();
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        // 예시: 사용자 권한 반환
////        return Collections.emptyList();
////    }
////
////    @Override
////    public String getPassword() {
////        return user.getPassword();
////    }
////
////    @Override
////    public String getUsername() {
////        return user.getUsername();
////    }
////
////    @Override
////    public boolean isAccountNonExpired() {
////        return true; // 로직에 따라 다를 수 있음
////    }
////
////    @Override
////    public boolean isAccountNonLocked() {
////        return true; // 로직에 따라 다를 수 있음
////    }
////
////    @Override
////    public boolean isCredentialsNonExpired() {
////        return true; // 로직에 따라 다를 수 있음
////    }
////
////    @Override
////    public boolean isEnabled() {
////        return user.isEnabled();
////    }
//}