package com.example.boardapp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.boardapp.dto.SignUpRequest;
import com.example.boardapp.mapper.MemberMapper;

import jakarta.validation.Valid;

import com.example.boardapp.domain.Member;

@Controller  //컨트롤러 빈으로 등록한다
@RequestMapping("/member") //URL prefix: 이 컨트롤러가 다루는 모든 URL 앞에 이 값을 붙여줌
public class MemberController{ //멤버 관련된 요청/응답을 처리하는 컨트롤러
   
    // 의존성 선언
    private final MemberMapper memberMapper; //멤버 데이터를 다루는 용도
    private final PasswordEncoder passwordEncoder; //비밀번호 암호화하는 객체

    public MemberController (MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper= memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

      //회원가입 페이지 요청을 처리하는 핸들러 (처리하는 사람)
      @GetMapping ("/join") //GET /join 요청을 처리한다 
      //Model: MVC(model - view - Controller) 에서 모델에 해당  
      // 모델의 역할: 데이터를 활용해 뷰를 업데이트 한다
       public String joinForm(Model model) {

        //뷰의 memberForm (회원 가입 폼) 과 가입요청 DTO (SignUpRequest )를 매핑
        // 사용자가 가입 할때 입력한 값이 이 DTO에 저장 
        model.addAttribute("memberForm", new SignUpRequest());

        //회원가입 페이지를 클라이언트에게 전송한다 (서버의 응답)
        return "member/join"; // 나중에 이 페이지를 만들거 
       }
      
       //회원가입 처리 핸들러
       @PostMapping("/join")// 요청을 처리할 주소 POST(데이터 생성 요청) /join(주소)
       // dto 매개변수 앞에 두개의 어노테이션 @Valid , @ModelAttribute 가 붙은 상태
       // @Valid: dto 대상으로 사전에 정의 유효성 검사를 실행해라
       // @ModelAttribute: 가입 페이지의 MemberForm (회원가입 폼)과 가입요청 dto를 매핑
       // BindingResult: validation 이 가입요청 DTO (SignUpRequest)를 대상으로 유효성 검증을 마치고
       //결과를 담은 객체 
       
       public String join(@Valid @ModelAttribute("memberForm") SignUpRequest dto, BindingResult
       bindingResult) {

        //비밀번호와 비밀번호 확인 (password confirm ) 이 일치 하지 않을 때 
        if (!dto.getPassword().equals(dto.getPasswordConfirm())) {
            // 이 경우 bindingResult 에 직접 에러를 추가 한다
            // bindingResult 에 에러 수집 하는중..
            // rejectValue (필드, 에러코드 , 에러 메시지)
            bindingResult.rejectValue("passwordConfirm", "passwordMismatch",
                "비밀번호가 일치하지 않습니다");
        }
       
        //사용자가 입력한 유저네임이 데이터 베이스에 존재하는지 검사 한다
        //dto.getUsername(): 사용자가 가입할때 입력한 유저네임(아이디)
        // isPresent: 옵셔널의 메서드 내부에 값이 존재하는지 확인 (boolean 타입으로 결과 반환)
        if(memberMapper.findByUsername(dto.getUsername()). isPresent()) {
            //아이디 중복 오류를 bindingResult 에 새롭게 추가
            bindingResult.rejectValue("username", "duplicatedUsername",
                "이미 존재하는 아이디입니다" );
        } 
   
        // bindingResult 에 에러가 있다면
        //에러: valiidation 이 추가한 에러, 우리가 직접 추가한 에러등
        if(bindingResult.hasErrors()) {
            // 에러메시지와 함께 가입 페이지를 다시 전송
            return "member/join";
        }
   
        // 모든 검사를 통과 한 경우 가입 절차를 시작한다
        
        //멤버 객체 생성 한다
        //비밀번호를 암호화해서 저장한다 
      Member member = new Member (dto.getUsername(), passwordEncoder.encode(dto.getPassword()));

        // 데이터 베이스에 저장한다 
        memberMapper.save(member);

        // 로그인 요청 핸들러로 이동 한다
        return "redirect:/member/login";

    }
        // 로그인 페이지 요청 핸들러 
        @GetMapping("/login") //GET /login 요청을 처리
        public String loginForm() { 
            //클라이언트에게 로그인 페이지를 전송 한다 
            return "member/login";
        }
    }

    
      
       
    
    
