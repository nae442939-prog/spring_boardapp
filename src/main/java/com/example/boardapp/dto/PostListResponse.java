package com.example.boardapp.dto;

//게시물 목록 응답 DTO

public class PostListResponse {
    
    private Long id;
    private String title;
    // 본문은 없음
    private String memberName; //작성자 유저네임

    public PostListResponse () {}

    //Getter/Setter
    public Long getId () {
        return id;
    }
    public void  setId (Long id) {
        this.id = id;
    }
    public String getTitle () {
        return title;
    }
     public void setTitle (String title) {
        this.title = title;
     }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName (String memberName) {
        this.memberName = memberName;
    }
}
