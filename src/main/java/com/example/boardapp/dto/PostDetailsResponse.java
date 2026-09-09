package com.example.boardapp.dto;

//게시물 상세보기 응답 DTO

public class PostDetailsResponse {

    private Long id;
    private String title;
    private String content;
    private String memberName;

    public PostDetailsResponse () {} 

    //Getter/Setter
    public Long getId () {
        return id;
    }
    public void setId (Long id) {
        this.id = id;
    }
    public String getTitle () {
        return title;
    }
    public void setTitle (String title) {
        this.title = title;
    }
    public String getContent () {
        return content;
    }
    public void setContent (String content) {
        this.content = content;
    }
    public String getMemberName () {
        return memberName;
    }
    public void setMemberName (String memberName) {
        this.memberName = memberName;
    }



}
