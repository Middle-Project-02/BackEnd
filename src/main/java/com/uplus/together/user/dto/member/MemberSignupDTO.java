package com.uplus.together.user.dto.member;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSignupDTO {
    private Long id;
    private String member_email;
    private String member_password;
    private String member_name;
    private String member_nickname;
}