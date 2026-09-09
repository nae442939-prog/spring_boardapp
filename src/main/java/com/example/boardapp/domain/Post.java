package com.example.boardapp.domain;

//Post 도메인: Post 테이블의 데이터를 다룰 때 사용 
 
public class Post {

    private Long id;
    private String title; //게시물 제목
    private String content; //본문
    //게시물 작성자
    private Member member;
    
    //기본 생성자
    public Post () {}

    //게시물 생성용 생성자
    public Post (String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
    
    //Getter
    public Long getId() {
        return id;
    }
    public String getTitle () {
        return title;
    }
    public String getContent () {
        return content;
   }
    public Member getMember() {
        return member;
    }
}
