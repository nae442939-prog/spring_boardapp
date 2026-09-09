package com.example.boardapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//회원 가입 요청용 DTO

public class SignUpRequest {

    //사용자 이름의 길이 검증(validation)
    // message: 검증에 실패했을 때 전달할 메시지 
    @Size (min = 4, max = 20, message = " 아이디는 4 자 이상 20자 이하로 입력하세요")
    private String username;

    //비밀 번호 길이 검증 (최소 6 글자)
    @Size(min = 6, message = "비밀번호는 최소 6 글자 이상이어야 합니다.")
    private String password;

    //NotBlank:  빈 값 (blank) 안됨
    // passwordConfirm :비밀 번호 재입력 란의 정보를 저장할 필드 
    @NotBlank (message = " 비밀 번호 재입력은 필수 항목입니다 " )
    private String passwordConfirm;

    public SignUpRequest () {}

    //Getter/Setter
    public String getUsername () {
        return username;
  }

    public void setUsername (String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    
    }
     public void setPassword(String password) {
        this.password = password;
     }

     public String getPasswordConfirm () {
        return passwordConfirm;
     }
    
      public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
      }
    }
