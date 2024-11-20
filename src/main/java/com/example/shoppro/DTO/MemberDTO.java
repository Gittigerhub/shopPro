package com.example.shoppro.DTO;

import com.example.shoppro.constant.Role;
import com.example.shoppro.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.naming.Name;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    // 어차피 등록시에는 안씀 // 유효성 제약조건 안잡음
    private Long id;

    @NotBlank(message = "· 이름을 작성해주세요.")
    @Size(min = 2, max = 10, message = "· 이름은 2자 이상 10자 이하로 작성해주세요.")
    private String name;

    @Email(message = "· 이메일 형식으로 작성해주세요.")
    @NotBlank(message = "· 이메일을 작성해주세요.")
    @Size(min = 2, max = 20, message = "· 이메일은 2자 이상 20자 이하로 작성해주세요.")
    private String email;

    @NotBlank(message = "· 비밀번호를 작성해주세요.")
    @Size(min = 4, max = 10, message = "· 비밀번호는 4자 이상 10자 이하로 작성해주세요.")
    private String password;

    private String address;

    private Role role;

    public Member dtoTOEntity (MemberDTO memberDTO) {
        // 패스워드 암호화
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setAddress(memberDTO.getAddress());

        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        // 권한 부여
        member.setRole(Role.ADMIN);

        return member;

    }


}
