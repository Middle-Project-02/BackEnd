package com.uplus.together.user.controller;

import com.uplus.together.user.dto.member.MemberSignupDTO;
import com.uplus.together.user.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/together/users")
@RequiredArgsConstructor

public class MemberController {

    @Autowired
    private MemberService memberService;

    private  static final Logger logger
            = LoggerFactory.getLogger(MemberController.class);


    //회원가입
    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> JoinRegister(@RequestBody MemberSignupDTO memberSignupDTO) {
        String member_password = memberSignupDTO.getMember_password();
        String member_name = memberSignupDTO.getMember_name();
        String member_nickname = memberSignupDTO.getMember_nickname();
        String member_email = memberSignupDTO.getMember_email();


        // 이메일 중복 검사
        if (memberService.isEmailDuplicated(member_email)) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "이미 있는 이메일입니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // 닉네임 중복 검사
        if (memberService.isIdDuplicated(member_nickname)) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "이미 있는 닉네임입니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }


        boolean result = memberService.register(member_email, member_password, member_name, member_nickname);
        if (result) {
            // 성공 메시지와 사용자 데이터를 담은 응답 생성
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> userData = new HashMap<>();

            userData.put("member_email", member_email);
            userData.put("member_nickname", member_nickname);
            userData.put("member_name", member_name);

            response.put("status", "success");
            response.put("message", "회원 가입 성공");
            response.put("data", userData);

            return ResponseEntity.ok(response);
        } else {
            // 실패 메시지를 담은 응답 생성
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "회원가입에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //아이디 중복확인 (닉네임)
    @GetMapping("/checkIdDuplicate/{member_nickname}")
    public ResponseEntity<Map<String, Boolean>> checkIdDuplicate(@PathVariable String member_nickname) {
        logger.info("중복검사 넘어온 아이디 : "+member_nickname);

        boolean duplicate = memberService.isIdDuplicated(member_nickname);

        logger.info("아이디 중복검사 : "+duplicate);
        Map<String, Boolean> response = Collections.singletonMap("duplicate", duplicate);
        return ResponseEntity.ok(response);
    }


    //이메일 중복확인
    @GetMapping("/checkEmailDuplicate/{member_email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailDuplicate(@PathVariable String member_email) {
        logger.info("중복검사 넘어온 이메일 : "+member_email);

        boolean duplicate = memberService.isEmailDuplicated(member_email);

        logger.info("이메일 중복검사 : "+duplicate);
        Map<String, Boolean> response = Collections.singletonMap("duplicate", duplicate);
        return ResponseEntity.ok(response);
    }



}