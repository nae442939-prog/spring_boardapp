package com.example.boardapp.domain;

//도메인 (Domain) : 데이터베이스의 테이블과 매핑되는 객체 
//Member 도메인으로 멤버 데이터를 다룰 수 있다

public class Member {
  
    //멤버 테이블 (DB)과 유사한 구조
    private Long id; //Long(래퍼클래스) /long 둘다 됨 데이터베이스의 BIGINT에 대응
    private String username;
    private String password;

    //기본 생성자 - 기본적으로 추가하는 것이 좋음
    public Member () {}

    //멤버 생성용 생성자 (회원가입 등)
    public Member (String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    //Getter
    public Long getId () {
        return id;
    }
 
    public String getUsername () {
        return username;
    }

    public String getPassword () {
        return password;
    }
}
