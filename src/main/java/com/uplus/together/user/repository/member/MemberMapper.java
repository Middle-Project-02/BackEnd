package com.uplus.together.user.repository.member;

import com.uplus.together.user.dto.member.MemberSignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    //회원가입
    int insert(MemberSignupDTO memberDTO);

    //아이디 중복검사
    boolean isIdDuplicated(String member_nickname);

    //이메일 중복검사
    boolean isEmailDuplicated(String member_email);

}
