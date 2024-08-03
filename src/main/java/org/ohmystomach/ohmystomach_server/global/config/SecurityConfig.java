package org.ohmystomach.ohmystomach_server.global.config;

//import org.ohmystomach.ohmystomach_server.global.common.util.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final JwtTokenProvider jwtTokenProvider;
////
//    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//    private final CustomOAuth2UserService oAuth2UserService;
//    private final OAuth2SuccessHandler oAuth2SuccessHandler;
//    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    private static final String[] WHITE_LIST = {
            "/oauth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/api/**", "/error/**", "/", "/swagger", "/swagger/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable) // 기본 인증 로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable) // 기본 login form 비활성화
                .logout(AbstractHttpConfigurer::disable) // 기본 logout 비활성화
                .authorizeHttpRequests(request -> request
//                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).authenticated()
                        .requestMatchers(WHITE_LIST).permitAll()
//                        .anyRequest().authenticated()
                )

                // oauth2 설정
//                .oauth2Login(oauth -> // OAuth2 로그인 기능에 대한 여러 설정의 진입점
//                        // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
//                        oauth.userInfoEndpoint(c -> c.userService(oAuth2UserService))
//                                // 로그인 성공 시 핸들러
//                                .successHandler(oAuth2SuccessHandler)
//                )
//
//                // jwt 관련 설정
//                .addFilterBefore(tokenAuthenticationFilter,
//                        UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new TokenExceptionFilter(), tokenAuthenticationFilter.getClass()); // 토큰 예외 핸들링

                .formLogin(withDefaults());

//        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//
        return http.build();
    }
}
