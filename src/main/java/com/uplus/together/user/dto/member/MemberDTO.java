package com.uplus.together.user.dto.member;


import lombok.*;

import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private String id;

    private String member_password;     //비밀번호

    private String member_email;    //이메일

    private String member_name;     //이름

    private String member_nickname;     //닉네임


    private int delflag;

}