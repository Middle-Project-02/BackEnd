package com.uplus.together.user.service.login;

import com.uplus.together.user.dto.member.MemberDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginService { // username(UserDetailsService 인터페이스의 일부로서, Spring Security에서 제공)은 user id
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    public MemberDTO checkLogin(String username, String password) throws Exception;

    String getRefreshToken(String userEmail) throws Exception;

}
