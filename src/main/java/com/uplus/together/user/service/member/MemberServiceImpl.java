package com.uplus.together.user.service.member;

import com.uplus.together.user.dto.member.MemberSignupDTO;
import com.uplus.together.user.dto.member.MemberDTO;
import com.uplus.together.user.repository.member.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
    private  static final Logger logger
            = LoggerFactory.getLogger(MemberServiceImpl.class);
    @Autowired
    private  MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //암호화 적용

    @Override
    public boolean register(String member_email, String member_password, String member_name,
                            String member_nickname) {

        MemberSignupDTO memberDTO = new MemberSignupDTO();
        logger.info("암호화 전 비밀번호 : "+ member_password);
        String encodedPassword = passwordEncoder.encode(member_password);
        logger.info("암호화 후 비밀번호 : "+ encodedPassword);
        memberDTO.setMember_password(encodedPassword);
        memberDTO.setMember_name(member_name);
        memberDTO.setMember_nickname(member_nickname);
        memberDTO.setMember_email(member_email);


        int result = memberMapper.insert(memberDTO);

        return result == 1;
    }


    /* 닉네임 중복 검사 */
    @Override
    public boolean isIdDuplicated(String member_nickname) {
        logger.info("서비스 넘어온 nickname : "+ member_nickname);
        return memberMapper.isIdDuplicated(member_nickname);
    }


    /* 이메일 중복 검사 */
    @Override
    public boolean isEmailDuplicated(String member_email) {
        logger.info("서비스 넘어온 email : "+ member_email);
        return memberMapper.isEmailDuplicated(member_email);
    }

}